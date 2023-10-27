package com.igorbavand.vendasapi.modulos.venda.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igorbavand.vendasapi.config.PageRequest;
import com.igorbavand.vendasapi.modulos.cliente.service.ClienteService;
import com.igorbavand.vendasapi.modulos.comum.exception.BadRequestException;
import com.igorbavand.vendasapi.modulos.comum.exception.NotFoundException;
import com.igorbavand.vendasapi.modulos.ingresso.service.IngressoService;
import com.igorbavand.vendasapi.modulos.venda.dto.VendaRequest;
import com.igorbavand.vendasapi.modulos.venda.dto.VendaResponse;
import com.igorbavand.vendasapi.modulos.venda.mapper.VendaMapper;
import com.igorbavand.vendasapi.modulos.venda.model.Venda;
import com.igorbavand.vendasapi.modulos.venda.repository.VendaRepository;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VendaService {

    private static final Long UM = 1L;
    private static final String STATUS_PAGAMENTO_PAGO = "paid";

    private final VendaRepository repository;
    private final VendaMapper mapper;
    private final IngressoService ingressoService;
    private final ClienteService clienteService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${app-config.rabbit.queues.enviar-email.exchange}")
    private String enviarEmailExchange;

    @Value("${app-config.rabbit.queues.enviar-email.key}")
    private String enviarEmailKey;

    @Value("${app-config.url}")
    private String urlBaseApi;

    @Value("${app-config.stripe.secretKey}")
    private String stripeSecretKey;

    @Value("${app-config.webhook.secretKey}")
    private String webhookSecretKey;

    @Value("${app-config.url}")
    private String url;

    public VendaResponse cadastrar(VendaRequest vendaRequest) {
        var venda = mapper.toVenda(vendaRequest);

        validarQuantidadeIngressosPorCliente(vendaRequest.getCliente(), vendaRequest.getIngresso());
        venda.aguardandoPagamento();
        venda.gerarCodigoVenda();
        venda.setCliente(clienteService.findById(vendaRequest.getCliente()));
        venda.setIngresso(ingressoService.findById(vendaRequest.getIngresso()));
        repository.save(venda);

        enviarEmailComIngresso(venda);

        return mapper.toVendaResponse(venda);
    }

    public String createCheckoutSession(VendaRequest vendaRequest) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        var venda = mapper.toVenda(vendaRequest);
        var cliente = clienteService.findById(vendaRequest.getCliente());
        var ingresso = ingressoService.findById(vendaRequest.getIngresso());

        //validarQuantidadeIngressosPorCliente(vendaRequest.getCliente(), vendaRequest.getIngresso());
        venda.aguardandoPagamento();
        venda.gerarCodigoVenda();
        venda.setCliente(cliente);
        venda.setIngresso(ingresso);

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(url + "/public/ingressos-pagamento/success?success=true")
                        .setCancelUrl(url + "/public/ingressos-pagamento/cancelled?cancelled=true")
                        .setCustomer(cliente.getCustomerId())
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(UM)
                                        .setPrice(ingresso.getPriceId())
                                        .build())
                        .build();
        Session session = Session.create(params);
        venda.setSessionId(session.getId());

        repository.save(venda);
        return session.getUrl();
    }

    public Page<VendaResponse> getAllVendas(PageRequest pageRequest) {
        return repository.findAll(pageRequest)
                .map(mapper::toVendaResponse);
    }

    public VendaResponse getVendaById(Integer id) {
        return mapper.toVendaResponse(repository.findById(id).orElseThrow(
                () -> new NotFoundException("Venda não encontrada.")
        ));
    }

    private void validarQuantidadeIngressosPorCliente(Integer cliente, Integer ingresso) {
        if (repository.countByClienteAndIngresso(cliente, ingresso) >= 2) {
            throw new BadRequestException("Número de ingressos por cliente esgotado");
        }
    }

    private void enviarEmailComIngresso(Venda venda) {
        var informacoesIngresso = mapper.toEnviarIngressoClienteEmailMqDto(venda);
        rabbitTemplate.convertAndSend(enviarEmailExchange, enviarEmailKey, informacoesIngresso);
    }

    public ResponseEntity<String> webhookListener(String payload, String sigHeader) throws Exception {
        Event event = null;
        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecretKey);
        } catch (SignatureVerificationException e) {
            log.info("Failed signature verification = {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        switch (event.getType()) {
        case "payment_intent.succeeded":
            // ...
            break;
        case "payment_method.attached":
            // ...
            break;
        case "payment_intent.created":
            //...
            break;
        case "customer.created":
            // ...
            break;
        case "charge.succeeded":
            // cobrança realizada com sucesso
            break;
        case "checkout.session.completed":
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(event.toJson());
                var csId = jsonNode.get("data").get("object").get("id").asText();
                var statusPagamento = jsonNode.get("data").get("object").get("payment_status").asText();

                var venda = repository.findBySessionId(csId).orElseThrow(
                        () -> new NotFoundException("Venda não localizada.")
                );

                if (statusPagamento.equalsIgnoreCase(STATUS_PAGAMENTO_PAGO)) {
                    enviarEmailComIngresso(venda);
                    venda.vendaPaga();
                } else {
                    venda.vendaNaoAutorizada();;
                    throw new BadRequestException("Pagamento não finalizado.");
                }

                repository.save(venda);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        default:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

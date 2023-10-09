package com.igorbavand.vendasapi.modulos.venda.service;

import com.igorbavand.vendasapi.config.PageRequest;
import com.igorbavand.vendasapi.modulos.cliente.service.ClienteService;
import com.igorbavand.vendasapi.modulos.ingresso.service.IngressoService;
import com.igorbavand.vendasapi.modulos.venda.dto.VendaRequest;
import com.igorbavand.vendasapi.modulos.venda.dto.VendaResponse;
import com.igorbavand.vendasapi.modulos.venda.mapper.VendaMapper;
import com.igorbavand.vendasapi.modulos.venda.model.Venda;
import com.igorbavand.vendasapi.modulos.venda.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository repository;
    private final VendaMapper mapper;
    private final IngressoService ingressoService;
    private final ClienteService clienteService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${app-config.rabbit.queues.enviar-email.exchange}")
    private String enviarEmailExchange;

    @Value("${app-config.rabbit.queues.enviar-email.key}")
    private String enviarEmailKey;

    public VendaResponse cadastrar(VendaRequest vendaRequest) {
        var venda = mapper.toVenda(vendaRequest);
        validarQuantidadeIngressosPorCliente(vendaRequest.getCliente(), vendaRequest.getIngresso());
        venda.vendaValidada();
        venda.gerarCodigoVenda();
        venda.setCliente(clienteService.findById(vendaRequest.getCliente()));
        venda.setIngresso(ingressoService.findById(vendaRequest.getIngresso()));
        repository.save(venda);

        enviarEmailComIngresso(venda);

        return mapper.toVendaResponse(venda);
    }

    public Page<VendaResponse> getAllVendas(PageRequest pageRequest) {
        return repository.findAll(pageRequest)
                .map(mapper::toVendaResponse);
    }

    public VendaResponse getVendaById(Integer id) {
        return mapper.toVendaResponse(repository.findById(id).get());
    }

    private void validarQuantidadeIngressosPorCliente(Integer cliente, Integer ingresso) {
        if (repository.countByClienteAndIngresso(cliente, ingresso) >= 2) {
            throw new RuntimeException("Número de ingressos por cliente esgotado");
        }
    }

    private void enviarEmailComIngresso(Venda venda) {
        var informacoesIngresso = mapper.toEnviarIngressoClienteEmailMqDto(venda);
        rabbitTemplate.convertAndSend(enviarEmailExchange, enviarEmailKey, informacoesIngresso);
    }
}

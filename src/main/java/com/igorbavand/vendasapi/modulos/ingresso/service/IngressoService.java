package com.igorbavand.vendasapi.modulos.ingresso.service;

import com.igorbavand.vendasapi.modulos.comum.exception.NotFoundException;
import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoRequest;
import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoResponse;
import com.igorbavand.vendasapi.modulos.ingresso.mapper.IngressoMapper;
import com.igorbavand.vendasapi.modulos.ingresso.model.Ingresso;
import com.igorbavand.vendasapi.modulos.ingresso.repository.IngressoRepository;
import com.igorbavand.vendasapi.modulos.stripe.service.StripeService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.Stripe;

@Service
@RequiredArgsConstructor
public class IngressoService {

    private final IngressoRepository repository;
    private final IngressoMapper mapper;
    private final StripeService stripeService;

    @Value("${app-config.stripe.secretKey}")
    private String stripeSecretKey;

    public IngressoResponse cadastrar(IngressoRequest ingressoRequest) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        var product = stripeService.registerProduct(ingressoRequest);
        var price = stripeService.registerPrice(product.getId(), ingressoRequest.getValor());
        var ingressoBancoDeDados = mapper.toIngresso(ingressoRequest);

        ingressoBancoDeDados.setProductId(product.getId());
        ingressoBancoDeDados.setPriceId(price.getId());

        repository.save(ingressoBancoDeDados);
        return mapper.toIngressoResponse(ingressoBancoDeDados);
    }

    public Ingresso findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Ingresso não encontrado.")
        );
    }
}

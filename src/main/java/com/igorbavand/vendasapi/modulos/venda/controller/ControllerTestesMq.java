package com.igorbavand.vendasapi.modulos.venda.controller;

import com.igorbavand.vendasapi.modulos.venda.rabbit.dto.EnviarIngressoClienteEmailMqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/testar-envio")
@RequiredArgsConstructor
public class ControllerTestesMq {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app-config.rabbit.queues.enviar-email.exchange}")
    private String enviarEmailExchange;

    @Value("${app-config.rabbit.queues.enviar-email.key}")
    private String enviarEmailKey;

    @GetMapping
    public void enviar() {

        var teste = new EnviarIngressoClienteEmailMqDto();
        teste.setCidadeCliente("knskdnsd");
        teste.setNomeCliente("lsmdslmd");
        teste.setValor(2.0);
        teste.setCodigoVenda("lasdjnflksdnflksndlfknsldf");

        rabbitTemplate.convertAndSend(enviarEmailExchange, enviarEmailKey, teste);

    }
}

package com.igorbavand.vendasapi.modulos.ingresso.controller;

import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoRequest;
import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoResponse;
import com.igorbavand.vendasapi.modulos.ingresso.service.IngressoService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ingresso")
public class IngressoController {

    private final IngressoService service;

    @PostMapping
    public ResponseEntity<IngressoResponse> cadastrar(@RequestBody IngressoRequest ingressoRequest) throws StripeException {
        return ResponseEntity.ok().body(service.cadastrar(ingressoRequest));
    }

}

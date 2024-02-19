package com.igorbavand.vendasapi.modulos.ingresso.controller;

import com.igorbavand.vendasapi.modulos.comum.annotations.PageRequestSwaggerParameters;
import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoRequest;
import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoResponse;
import com.igorbavand.vendasapi.modulos.ingresso.service.IngressoService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ingresso")
public class IngressoController {

    private final IngressoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IngressoResponse> cadastrar(@RequestBody IngressoRequest ingressoRequest) throws StripeException {
        return ResponseEntity.ok().body(service.cadastrar(ingressoRequest));
    }

    @GetMapping
    @PageRequestSwaggerParameters
    public List<IngressoResponse> getAll() {
        return service.getAll();
    }
}

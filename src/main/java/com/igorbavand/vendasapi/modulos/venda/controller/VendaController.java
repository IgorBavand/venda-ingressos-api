package com.igorbavand.vendasapi.modulos.venda.controller;

import com.igorbavand.vendasapi.config.PageRequest;
import com.igorbavand.vendasapi.modulos.comum.annotations.PageRequestSwaggerParameters;
import com.igorbavand.vendasapi.modulos.venda.dto.VendaRequest;
import com.igorbavand.vendasapi.modulos.venda.dto.VendaResponse;
import com.igorbavand.vendasapi.modulos.venda.service.VendaService;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/venda")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService service;

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody VendaRequest vendaRequest) throws StripeException {
        return ResponseEntity.ok().body(service.createCheckoutSession(vendaRequest));
    }

    @GetMapping
    @PageRequestSwaggerParameters
    public ResponseEntity<Page<VendaResponse>> getAllProdutos(@Parameter(hidden = true) PageRequest pageRequest) {
        return ResponseEntity.ok().body(service.getAllVendas(pageRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<VendaResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.accepted().body(service.getVendaById(id));
    }

    @PostMapping("webhook-listener")
    public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws Exception {
        return service.webhookListener(payload, sigHeader);
    }
}

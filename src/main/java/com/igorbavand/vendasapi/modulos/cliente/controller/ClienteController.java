package com.igorbavand.vendasapi.modulos.cliente.controller;

import com.igorbavand.vendasapi.config.PageRequest;
import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteRequest;
import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteResponse;
import com.igorbavand.vendasapi.modulos.cliente.filtros.ClienteFiltros;
import com.igorbavand.vendasapi.modulos.cliente.service.ClienteService;
import com.stripe.exception.StripeException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public Page<ClienteResponse> getAll(PageRequest pageRequest, ClienteFiltros filtros) {
        return service.getAll(pageRequest, filtros);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@Valid @RequestBody ClienteRequest clienteRequest) throws StripeException {
        return ResponseEntity.ok().body(service.cadastrar(clienteRequest));
    }
}

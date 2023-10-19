package com.igorbavand.vendasapi.modulos.cliente.controller;

import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteRequest;
import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteResponse;
import com.igorbavand.vendasapi.modulos.cliente.service.ClienteService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@Valid @RequestBody ClienteRequest clienteRequest) {
        return ResponseEntity.ok().body(service.cadastrar(clienteRequest));
    }
}

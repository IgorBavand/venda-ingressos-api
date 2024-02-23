package com.igorbavand.vendasapi.modulos.cliente.controller;

import com.igorbavand.vendasapi.config.PageRequest;
import com.igorbavand.vendasapi.modulos.autenticacao.dto.RegisterDto;
import com.igorbavand.vendasapi.modulos.autenticacao.dto.UserResponseDto;
import com.igorbavand.vendasapi.modulos.cliente.filtros.ClienteFiltros;
import com.igorbavand.vendasapi.modulos.cliente.service.ClienteService;
import com.igorbavand.vendasapi.modulos.comum.annotations.PageRequestSwaggerParameters;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Parameter;
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
    @PageRequestSwaggerParameters
    public Page<UserResponseDto> getAll(@Parameter(hidden = true) PageRequest pageRequest, ClienteFiltros filtros) {
        return service.getAll(pageRequest, filtros);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> cadastrar(@Valid @RequestBody RegisterDto clienteRequest) throws StripeException {
        return ResponseEntity.ok().body(service.cadastrar(clienteRequest));
    }
}

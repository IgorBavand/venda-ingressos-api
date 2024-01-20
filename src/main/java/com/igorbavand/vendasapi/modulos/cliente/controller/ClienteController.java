package com.igorbavand.vendasapi.modulos.cliente.controller;

import com.igorbavand.vendasapi.config.PageRequest;
import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteRequest;
import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteResponse;
import com.igorbavand.vendasapi.modulos.cliente.filtros.ClienteFiltros;
import com.igorbavand.vendasapi.modulos.cliente.service.ClienteService;
import com.igorbavand.vendasapi.modulos.comum.annotations.PageRequestSwaggerParameters;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public Page<ClienteResponse> getAll(@Parameter(hidden = true) PageRequest pageRequest, ClienteFiltros filtros) {
        return service.getAll(pageRequest, filtros);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@Valid @RequestBody ClienteRequest clienteRequest) throws StripeException {
        return ResponseEntity.ok().body(service.cadastrar(clienteRequest));
    }
}

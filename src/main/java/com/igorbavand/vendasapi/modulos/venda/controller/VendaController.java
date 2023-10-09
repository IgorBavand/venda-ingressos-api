package com.igorbavand.vendasapi.modulos.venda.controller;

import com.igorbavand.vendasapi.config.PageRequest;
import com.igorbavand.vendasapi.modulos.venda.dto.VendaRequest;
import com.igorbavand.vendasapi.modulos.venda.dto.VendaResponse;
import com.igorbavand.vendasapi.modulos.venda.service.VendaService;
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
    public ResponseEntity<VendaResponse> cadastrar(@RequestBody VendaRequest vendaRequest) {
        return ResponseEntity.ok().body(service.cadastrar(vendaRequest));
    }

    @GetMapping
    public ResponseEntity<Page<VendaResponse>> getAllProdutos(PageRequest pageRequest) {
        return ResponseEntity.ok().body(service.getAllVendas(pageRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<VendaResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.accepted().body(service.getVendaById(id));
    }

}

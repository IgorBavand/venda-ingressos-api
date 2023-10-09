package com.igorbavand.vendasapi.modulos.cliente.service;

import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteRequest;
import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteResponse;
import com.igorbavand.vendasapi.modulos.cliente.mapper.ClienteMapper;
import com.igorbavand.vendasapi.modulos.cliente.model.Cliente;
import com.igorbavand.vendasapi.modulos.cliente.repository.ClienteRepository;
import com.igorbavand.vendasapi.modulos.comum.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteResponse cadastrar(ClienteRequest clienteRequest) {
        var cliente = repository.save(mapper.toCliente(clienteRequest));
        return mapper.toClienteResponse(cliente);
    }

    public Cliente findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Cliente não encontrado.")
        );
    }
}

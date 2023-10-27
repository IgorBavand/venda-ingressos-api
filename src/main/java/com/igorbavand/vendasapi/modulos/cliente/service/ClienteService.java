package com.igorbavand.vendasapi.modulos.cliente.service;

import com.igorbavand.vendasapi.config.PageRequest;
import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteRequest;
import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteResponse;
import com.igorbavand.vendasapi.modulos.cliente.filtros.ClienteFiltros;
import com.igorbavand.vendasapi.modulos.cliente.mapper.ClienteMapper;
import com.igorbavand.vendasapi.modulos.cliente.model.Cliente;
import com.igorbavand.vendasapi.modulos.cliente.repository.ClienteRepository;
import com.igorbavand.vendasapi.modulos.comum.exception.NotFoundException;
import com.igorbavand.vendasapi.modulos.stripe.service.StripeService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final StripeService stripeService;

    public Page<ClienteResponse> getAll(PageRequest pageRequest, ClienteFiltros filtros) {
        return repository.findAll(filtros.toPredicate(), pageRequest).map(mapper::toClienteResponse);
    }

    public ClienteResponse cadastrar(ClienteRequest clienteRequest) throws StripeException {

        var cliente = mapper.toCliente(clienteRequest);
        var stripeCustomer = stripeService.registerCustomer(cliente);
        cliente.setCustomerId(stripeCustomer.getId());

        repository.save(cliente);
        return mapper.toClienteResponse(cliente);
    }

    public Cliente findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Cliente não encontrado.")
        );
    }
}

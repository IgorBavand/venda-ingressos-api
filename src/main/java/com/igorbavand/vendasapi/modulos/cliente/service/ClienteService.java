package com.igorbavand.vendasapi.modulos.cliente.service;

import com.igorbavand.vendasapi.config.PageRequest;
import com.igorbavand.vendasapi.modulos.autenticacao.dto.RegisterDto;
import com.igorbavand.vendasapi.modulos.autenticacao.dto.UserResponseDto;
import com.igorbavand.vendasapi.modulos.autenticacao.mapper.UsuarioMapper;
import com.igorbavand.vendasapi.modulos.autenticacao.model.Usuario;
import com.igorbavand.vendasapi.modulos.autenticacao.repository.UserRepository;
import com.igorbavand.vendasapi.modulos.cliente.filtros.ClienteFiltros;
import com.igorbavand.vendasapi.modulos.comum.exception.NotFoundException;
import com.igorbavand.vendasapi.modulos.stripe.service.StripeService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final UserRepository repository;
    private final UsuarioMapper mapper;
    private final StripeService stripeService;

    public Page<UserResponseDto> getAll(PageRequest pageRequest, ClienteFiltros filtros) {
        return repository.findAll(filtros.toPredicate(), pageRequest).map(mapper::toUserResponse);
    }

    public UserResponseDto cadastrar(RegisterDto clienteRequest) throws StripeException {

        var cliente = mapper.toUsuario(clienteRequest);
        var stripeCustomer = stripeService.registerCustomer(cliente);
        cliente.setCustomerId(stripeCustomer.getId());

        repository.save(cliente);
        return mapper.toUserResponse(cliente);
    }

    public Usuario findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Cliente não encontrado.")
        );
    }
}

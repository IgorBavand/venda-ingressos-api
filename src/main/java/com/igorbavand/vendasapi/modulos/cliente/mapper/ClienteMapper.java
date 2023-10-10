package com.igorbavand.vendasapi.modulos.cliente.mapper;

import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteRequest;
import com.igorbavand.vendasapi.modulos.cliente.dto.ClienteResponse;
import com.igorbavand.vendasapi.modulos.cliente.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toCliente(ClienteRequest clienteRequest);

    ClienteResponse toClienteResponse(Cliente cliente);
}

package com.igorbavand.vendasapi.modulos.venda.mapper;

import com.igorbavand.vendasapi.modulos.venda.dto.VendaRequest;
import com.igorbavand.vendasapi.modulos.venda.dto.VendaResponse;
import com.igorbavand.vendasapi.modulos.venda.model.Venda;
import com.igorbavand.vendasapi.modulos.venda.rabbit.dto.EnviarIngressoClienteEmailMqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendaMapper {

    @Mapping(source = "cliente", target = "cliente.id")
    @Mapping(source = "ingresso", target = "ingresso.id")
    @Mapping(target = "id", ignore = true)
    Venda toVenda(VendaRequest vendaRequest);

    VendaResponse toVendaResponse(Venda venda);

    @Mapping(source = "cliente.nome", target = "nomeCliente")
    @Mapping(source = "cliente.email", target = "emailCliente")
    @Mapping(source = "cliente.cidade", target = "cidadeCliente")
    @Mapping(source = "cliente.cpf", target = "cpfCliente")
    @Mapping(source = "ingresso.descricao", target = "descricaoEvento")
    @Mapping(source = "ingresso.localEvento", target = "localEvento")
    @Mapping(source = "ingresso.dataEvento", target = "dataEvento")
    @Mapping(source = "ingresso.valor", target = "valor")
    EnviarIngressoClienteEmailMqDto toEnviarIngressoClienteEmailMqDto(Venda venda);

}

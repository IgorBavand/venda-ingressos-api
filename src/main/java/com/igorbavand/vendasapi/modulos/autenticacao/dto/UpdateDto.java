package com.igorbavand.vendasapi.modulos.autenticacao.dto;

import com.igorbavand.vendasapi.modulos.autenticacao.enums.EUserRole;

public record UpdateDto(
        String nome,
        String login,
        String telefone,
        String cidade,
        EUserRole userRole
) {}


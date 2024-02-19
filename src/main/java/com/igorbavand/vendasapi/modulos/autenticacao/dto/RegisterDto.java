package com.igorbavand.vendasapi.modulos.autenticacao.dto;


import com.igorbavand.vendasapi.modulos.autenticacao.enums.EUserRole;


public record RegisterDto(
    String nome,
    String login,
    String password,
    String telefone,
    String cpf,
    String cidade,
    EUserRole userRole
) {}


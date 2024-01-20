package com.igorbavand.vendasapi.modulos.autenticacao.dto;


import com.igorbavand.vendasapi.modulos.autenticacao.enums.EUserRole;

public record RegisterDto(String login, String password, EUserRole userRole) { }

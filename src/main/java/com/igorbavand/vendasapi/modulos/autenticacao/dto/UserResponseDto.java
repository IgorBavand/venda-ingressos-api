package com.igorbavand.vendasapi.modulos.autenticacao.dto;

import com.igorbavand.vendasapi.modulos.autenticacao.enums.EUserRole;

import java.time.LocalDateTime;

public record UserResponseDto(Integer id, String login, EUserRole role, LocalDateTime createdAt, LocalDateTime updatedAt) { }

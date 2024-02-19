package com.igorbavand.vendasapi.modulos.autenticacao.dto;

import com.igorbavand.vendasapi.modulos.autenticacao.enums.EUserRole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record UserResponseDto(Integer id,
                              String nome,
                              String login,
                              String telefone,
                              String cpf,
                              String cidade,
                              EUserRole userRole,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt) {


}

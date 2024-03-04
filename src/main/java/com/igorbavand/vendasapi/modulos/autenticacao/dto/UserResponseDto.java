package com.igorbavand.vendasapi.modulos.autenticacao.dto;

import com.igorbavand.vendasapi.modulos.autenticacao.enums.EUserRole;
import com.igorbavand.vendasapi.modulos.autenticacao.model.Usuario;

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

    public static UserResponseDto fromUsuario(Usuario usuario) {
        return new UserResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getTelefone(),
                usuario.getCpf(),
                usuario.getCidade(),
                usuario.getRole(),
                usuario.getCreatedAt(),
                usuario.getUpdatedAt()
        );
    }
}

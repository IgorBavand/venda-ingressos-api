package com.igorbavand.vendasapi.modulos.autenticacao.mapper;

import com.igorbavand.vendasapi.modulos.autenticacao.dto.RegisterDto;
import com.igorbavand.vendasapi.modulos.autenticacao.dto.UserResponseDto;
import com.igorbavand.vendasapi.modulos.autenticacao.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toUsuario(RegisterDto registerDto);
    UserResponseDto toUserResponse(Usuario usuario);
}

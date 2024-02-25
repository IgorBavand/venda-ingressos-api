package com.igorbavand.vendasapi.modulos.autenticacao.service;

import com.igorbavand.vendasapi.modulos.autenticacao.dto.RegisterDto;
import com.igorbavand.vendasapi.modulos.autenticacao.model.Usuario;
import com.igorbavand.vendasapi.modulos.autenticacao.repository.UserRepository;
import com.igorbavand.vendasapi.modulos.comum.exception.BadRequestException;
import com.igorbavand.vendasapi.modulos.comum.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public ResponseEntity register(RegisterDto registerDto) {
        validateExistingUser(registerDto.login());
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        Usuario newUser = new Usuario(
                registerDto.nome(),
                registerDto.telefone(),
                registerDto.cpf(),
                registerDto.cidade(),
                registerDto.login(),
                encryptedPassword,
                registerDto.userRole());
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    public Usuario findByLogin(String login) {
        return repository.findByLogin(login).orElseThrow(() -> new NotFoundException("User not found."));
    }

    private void validateExistingUser(String login) {
        if (this.repository.findByLogin(login).isPresent()) {
            throw new BadRequestException("User already registred.");
        }
    }
}

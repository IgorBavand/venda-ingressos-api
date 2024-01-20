package com.igorbavand.vendasapi.modulos.autenticacao.repository;

import com.igorbavand.vendasapi.modulos.autenticacao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByLogin(String login);
}

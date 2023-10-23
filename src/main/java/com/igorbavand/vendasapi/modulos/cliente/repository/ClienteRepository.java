package com.igorbavand.vendasapi.modulos.cliente.repository;

import com.igorbavand.vendasapi.modulos.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, QuerydslPredicateExecutor<Cliente> {
}

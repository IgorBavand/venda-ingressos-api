package com.igorbavand.vendasapi.modulos.cliente.repository;

import com.igorbavand.vendasapi.modulos.cliente.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}

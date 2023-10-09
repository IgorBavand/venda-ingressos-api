package com.igorbavand.vendasapi.modulos.ingresso.repository;

import com.igorbavand.vendasapi.modulos.ingresso.model.Ingresso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends CrudRepository<Ingresso, Integer> {
}

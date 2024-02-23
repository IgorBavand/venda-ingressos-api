package com.igorbavand.vendasapi.modulos.ingresso.repository;

import com.igorbavand.vendasapi.modulos.ingresso.model.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Integer> {
}

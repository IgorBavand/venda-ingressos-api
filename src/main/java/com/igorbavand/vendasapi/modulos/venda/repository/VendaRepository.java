package com.igorbavand.vendasapi.modulos.venda.repository;

import com.igorbavand.vendasapi.modulos.venda.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Integer>, JpaRepository<Venda, Integer> {

    @Query("SELECT COUNT(v) FROM Venda v WHERE v.cliente.id = :cliente AND v.ingresso.id = :ingresso")
    Long countByClienteAndIngresso(Integer cliente, Integer ingresso);

}

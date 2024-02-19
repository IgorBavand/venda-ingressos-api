package com.igorbavand.vendasapi.modulos.ingresso.filtros;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngressoFiltros {

    private String descricao;
    private String localEvento;
    private LocalDate dataEvento;
    private LocalDate dataInicio;
    private LocalDate datafim;
}

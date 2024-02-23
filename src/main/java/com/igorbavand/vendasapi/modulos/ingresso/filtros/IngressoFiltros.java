package com.igorbavand.vendasapi.modulos.ingresso.filtros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngressoFiltros {

    private String descricao;
    private String localEvento;
    private LocalDateTime dataEvento;
    private LocalDate dataInicio;
    private LocalDate datafim;
}

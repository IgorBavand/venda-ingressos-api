package com.igorbavand.vendasapi.modulos.ingresso.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class IngressoResponse {
    private Integer id;
    private String descricao;
    private String localEvento;
    private LocalDate dataEvento;
    private LocalDate dataEncerramentoVenda;
    private Double valor;
}

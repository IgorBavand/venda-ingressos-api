package com.igorbavand.vendasapi.modulos.ingresso.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class IngressoRequest {
    private String descricao;
    private String localEvento;
    private LocalDateTime dataEvento;
    private LocalDate dataEncerramentoVenda;
    private Double valor;
}

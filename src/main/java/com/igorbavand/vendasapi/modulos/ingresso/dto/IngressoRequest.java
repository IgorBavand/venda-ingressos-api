package com.igorbavand.vendasapi.modulos.ingresso.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class IngressoRequest {
    private String descricao;
    private String localEvento;
    private LocalDateTime dataEvento;
    private LocalDate dataEncerramentoVenda;
    private Double valor;
}

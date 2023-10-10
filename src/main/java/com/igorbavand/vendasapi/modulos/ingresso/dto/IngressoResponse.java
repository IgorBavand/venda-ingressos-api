package com.igorbavand.vendasapi.modulos.ingresso.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class IngressoResponse {
    private Integer id;
    private String descricao;
    private String localEvento;
    private LocalDate dataEvento;
    private LocalDate dataEncerramentoVenda;
    private Double valor;
}

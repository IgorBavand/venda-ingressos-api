package com.igorbavand.vendasapi.modulos.venda.rabbit.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class EnviarIngressoClienteEmailMqDto {
    private String codigoVenda;
    private String nomeCliente;
    private String cidadeCliente;
    private String cpfCliente;
    private String descricaoEvento;
    private String localEvento;
    private LocalDateTime dataEvento;
    private Double valor;
}

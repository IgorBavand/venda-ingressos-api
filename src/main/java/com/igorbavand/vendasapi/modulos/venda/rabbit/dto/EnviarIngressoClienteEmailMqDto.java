package com.igorbavand.vendasapi.modulos.venda.rabbit.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EnviarIngressoClienteEmailMqDto {
    private String codigoVenda;
    private String nomeCliente;
    private String emailCliente;
    private String cidadeCliente;
    private String cpfCliente;
    private String descricaoEvento;
    private String localEvento;
    private LocalDateTime dataEvento;
    private Double valor;
}

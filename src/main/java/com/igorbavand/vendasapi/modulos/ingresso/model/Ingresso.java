package com.igorbavand.vendasapi.modulos.ingresso.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingresso")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "local_evento")
    private String localEvento;

    @Column(name = "data_evento")
    private LocalDate dataEvento;

    @Column(name = "data_encerramento_venda")
    private LocalDate dataEncerramentoVenda;  // AO ENCERRAR A DATA DE VENDAS DEVE SER ENVIADO UM EMAIL COM O RELATORIO DE VENDAS

    @Column(name = "valor")
    private Double valor;
}


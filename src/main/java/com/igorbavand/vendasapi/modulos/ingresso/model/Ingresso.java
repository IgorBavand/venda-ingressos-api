package com.igorbavand.vendasapi.modulos.ingresso.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDateTime dataEvento;

    @Column(name = "data_encerramento_venda")
    private LocalDate dataEncerramentoVenda;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "price_id")
    private String priceId;
}


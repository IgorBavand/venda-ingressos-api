package com.igorbavand.vendasapi.modulos.venda.model;

import com.igorbavand.vendasapi.modulos.cliente.model.Cliente;
import com.igorbavand.vendasapi.modulos.ingresso.model.Ingresso;
import com.igorbavand.vendasapi.modulos.venda.enums.EStatusVenda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_ingresso")
    private Ingresso ingresso;

    @Column(name = "status_venda")
    @Enumerated(EnumType.STRING)
    private EStatusVenda statusVenda;

    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    @Column(name = "codigo_venda")
    private String codigoVenda;

    @PrePersist
    public void prePersist() {
        dataVenda = LocalDateTime.now();
    }

    public void vendaValidada() {
        this.statusVenda = EStatusVenda.APROVADA;
    }

    public void vendaNegada() {
        this.statusVenda = EStatusVenda.QUANTIDADE_ESGOTADA_PARA_CPF;
    }

    public void gerarCodigoVenda() {
        this.codigoVenda = UUID.randomUUID().toString();
    }
}

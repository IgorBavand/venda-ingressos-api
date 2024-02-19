package com.igorbavand.vendasapi.modulos.venda.model;

import com.igorbavand.vendasapi.modulos.autenticacao.model.Usuario;
import com.igorbavand.vendasapi.modulos.ingresso.model.Ingresso;
import com.igorbavand.vendasapi.modulos.venda.enums.EStatusVenda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Usuario cliente;

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

    @Column(name = "session_id")
    private String sessionId;

    @PrePersist
    public void prePersist() {
        dataVenda = LocalDateTime.now();
    }

    public void vendaPaga() {
        this.statusVenda = EStatusVenda.PAGO;
    }

    public void aguardandoPagamento() {
        this.statusVenda = EStatusVenda.AGUARDANDO_PAGAMENTO;
    }

    public void vendaNaoAutorizada() {
        this.statusVenda = EStatusVenda.VENDA_NAO_AUTORIZADA;
    }

    public void gerarCodigoVenda() {
        this.codigoVenda = UUID.randomUUID().toString();
    }
}

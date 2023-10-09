package com.igorbavand.vendasapi.modulos.venda.dto;

import com.igorbavand.vendasapi.modulos.cliente.model.Cliente;
import com.igorbavand.vendasapi.modulos.ingresso.model.Ingresso;
import com.igorbavand.vendasapi.modulos.venda.enums.EStatusVenda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaResponse {
    private Integer id;
    private Cliente cliente;
    private Ingresso ingresso;
    private EStatusVenda statusVenda;
    private LocalDateTime dataVenda;
}

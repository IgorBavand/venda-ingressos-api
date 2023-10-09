package com.igorbavand.vendasapi.modulos.cliente.dto;

import lombok.Data;

@Data
public class ClienteRequest {
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String cidade;
}

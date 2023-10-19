package com.igorbavand.vendasapi.modulos.cliente.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ClienteRequest {

    @NotEmpty
    private String nome;

    @NotNull
    @Email
    private String email;

    @NotEmpty
    private String telefone;

    @NotEmpty
    private String cpf;

    @NotEmpty
    private String cidade;
}

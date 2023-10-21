package com.igorbavand.vendasapi.modulos.cliente.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
public class ClienteRequest {

    @NotEmpty
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String telefone;

    @NotEmpty
    @CPF
    private String cpf;

    @NotEmpty
    private String cidade;
}

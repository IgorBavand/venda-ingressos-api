package com.igorbavand.vendasapi.modulos.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

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

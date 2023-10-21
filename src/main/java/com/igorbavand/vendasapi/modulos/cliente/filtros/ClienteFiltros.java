package com.igorbavand.vendasapi.modulos.cliente.filtros;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.igorbavand.vendasapi.modulos.cliente.predicate.ClientePredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFiltros {

    private String nome;
    private String email;
    private String cpf;
    private String cidade;

    @JsonIgnore
    public BooleanBuilder toPredicate() {
        return new ClientePredicate()
                .comNome(this.nome)
                .comEmail(this.email)
                .comCpf(this.cpf)
                .comCidade(this.cidade)
                .build();
    }
}

package com.igorbavand.vendasapi.modulos.cliente.predicate;

import com.igorbavand.vendasapi.modulos.cliente.model.QCliente;
import com.querydsl.core.BooleanBuilder;
import org.springframework.util.StringUtils;

public class ClientePredicate {

    private static final String PORCENTAGEM_STRING = "%";

    private final QCliente cliente = QCliente.cliente;

    private final BooleanBuilder builder;

    public ClientePredicate() {
        this.builder = new BooleanBuilder();
    }

    public ClientePredicate comNome(String nome) {
        if (!StringUtils.isEmpty(nome)) {
            builder.and(cliente.nome.likeIgnoreCase(PORCENTAGEM_STRING + nome + PORCENTAGEM_STRING));
        }
        return this;
    }

    public ClientePredicate comEmail(String email) {
        if (!StringUtils.isEmpty(email)) {
            builder.and(cliente.email.eq(email));
        }
        return this;
    }

    public ClientePredicate comCpf(String cpf) {
        if (!StringUtils.isEmpty(cpf)) {
            builder.and(cliente.cpf.eq(cpf));
        }
        return this;
    }

    public ClientePredicate comCidade(String cidade) {
        if (!StringUtils.isEmpty(cidade)) {
            builder.and(cliente.cidade.likeIgnoreCase(PORCENTAGEM_STRING + cidade + PORCENTAGEM_STRING));
        }
        return this;
    }

    public BooleanBuilder build() {
        return this.builder;
    }
}

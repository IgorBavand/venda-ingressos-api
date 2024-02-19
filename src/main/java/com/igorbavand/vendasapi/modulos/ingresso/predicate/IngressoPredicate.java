package com.igorbavand.vendasapi.modulos.ingresso.predicate;

import com.igorbavand.vendasapi.modulos.ingresso.model.QIngresso;
import com.querydsl.core.BooleanBuilder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class IngressoPredicate {

    private static final String PORCENTAGEM_STRING = "%";
    private final QIngresso ingresso = QIngresso.ingresso;
    private final BooleanBuilder builder;

    public IngressoPredicate() {
        this.builder = new BooleanBuilder();
    }

    public IngressoPredicate comDescricao(String descricao) {
        if (!StringUtils.isEmpty(descricao)) {
            builder.and(ingresso.descricao.likeIgnoreCase(PORCENTAGEM_STRING + descricao + PORCENTAGEM_STRING));
        }
        return this;
    }

    public IngressoPredicate comLocal(String localEvento) {
        if (!StringUtils.isEmpty(localEvento)) {
            builder.and(ingresso.descricao.likeIgnoreCase(PORCENTAGEM_STRING + localEvento + PORCENTAGEM_STRING));
        }
        return this;
    }

}

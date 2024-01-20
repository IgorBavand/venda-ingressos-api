package com.igorbavand.vendasapi.modulos.comum.annotations;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Parameter(
        in = ParameterIn.QUERY,
        name = "page",
        description = "PageRequest - Filtro de número da página.",
        schema = @Schema(type = "Integer", defaultValue = "0"))
@Parameter(
        in = ParameterIn.QUERY,
        name = "size",
        description = "PageRequest - Filtro de quantidade de elementos por página.",
        schema = @Schema(type = "Integer", defaultValue = "10"))
@Parameter(
        in = ParameterIn.QUERY,
        name = "orderBy",
        description = "PageRequest - Filtro de ordenação da página.",
        schema = @Schema(type = "String", defaultValue = "id"))
@Parameter(
        in = ParameterIn.QUERY,
        name = "orderDirection",
        description = "PageRequest - Filtro de critério de ordenação.",
        schema = @Schema(type = "String", defaultValue = "asc", allowableValues = {"asc", "desc"}))
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface PageRequestSwaggerParameters {
}


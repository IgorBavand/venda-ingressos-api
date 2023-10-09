package com.igorbavand.vendasapi.modulos.ingresso.mapper;

import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoRequest;
import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoResponse;
import com.igorbavand.vendasapi.modulos.ingresso.model.Ingresso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngressoMapper {

    Ingresso toIngresso(IngressoRequest ingressoRequest);

    IngressoResponse toIngressoResponse(Ingresso ingresso);

}

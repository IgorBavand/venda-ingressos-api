package com.igorbavand.vendasapi.modulos.ingresso.service;

import com.igorbavand.vendasapi.modulos.comum.exception.NotFoundException;
import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoRequest;
import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoResponse;
import com.igorbavand.vendasapi.modulos.ingresso.mapper.IngressoMapper;
import com.igorbavand.vendasapi.modulos.ingresso.model.Ingresso;
import com.igorbavand.vendasapi.modulos.ingresso.repository.IngressoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngressoService {

    private final IngressoRepository repository;
    private final IngressoMapper mapper;

    public IngressoResponse cadastrar(IngressoRequest ingressoRequest) {
        var ingresso = repository.save(mapper.toIngresso(ingressoRequest));
        return mapper.toIngressoResponse(ingresso);
    }

    public Ingresso findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Ingresso não encontrado.")
        );
    }
}

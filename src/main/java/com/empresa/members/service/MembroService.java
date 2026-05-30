package com.empresa.members.service;

import com.empresa.members.enums.CargoMembro;
import com.empresa.members.config.exception.RecursoNaoEncontradoException;
import com.empresa.members.model.Membro;
import com.empresa.members.dto.request.MembroRequest;
import com.empresa.members.dto.response.MembroResponse;
import com.empresa.members.repository.MembroRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MembroService {

    private final MembroRepository repository;

    public MembroService(MembroRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public MembroResponse criar(MembroRequest request) {
        Membro membro = repository.save(Membro.builder()
                .nome(request.nome())
                .cargo(request.cargo())
                .build());
        return toResponse(membro);
    }

    public MembroResponse buscar(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Membro", id));
    }

    public List<MembroResponse> listar(CargoMembro cargo) {
        if(ObjectUtils.isNotEmpty(cargo)) {
            return repository.findByCargo(cargo).stream().map(this::toResponse).toList();
        }

        return repository.findAll().stream().map(this::toResponse).toList();
    }

    private MembroResponse toResponse(Membro m) {
        return new MembroResponse(m.getId(), m.getNome(), m.getCargo());
    }
}

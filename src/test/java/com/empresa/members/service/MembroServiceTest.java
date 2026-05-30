package com.empresa.members.service;

import com.empresa.members.enums.CargoMembro;
import com.empresa.members.config.exception.RecursoNaoEncontradoException;
import com.empresa.members.model.Membro;
import com.empresa.members.dto.request.MembroRequest;
import com.empresa.members.dto.response.MembroResponse;
import com.empresa.members.repository.MembroRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MembroServiceTest {

    @Mock private MembroRepository repository;

    @InjectMocks
    private MembroService service;

    @Test
    @DisplayName("criar persiste membro com nome e cargo")
    void criar() {
        Membro persisted = Membro.builder().nome("Maria").cargo(CargoMembro.FUNCIONARIO).build();
        when(repository.save(any(Membro.class))).thenReturn(persisted);

        MembroResponse result = service.criar(new MembroRequest("Maria", CargoMembro.FUNCIONARIO));

        assertThat(result.nome()).isEqualTo("Maria");
        assertThat(result.cargo()).isEqualTo(CargoMembro.FUNCIONARIO);
    }

    @Test
    @DisplayName("buscar retorna membro existente")
    void buscar() {
        Membro persisted = Membro.builder().nome("Maria").cargo(CargoMembro.GERENTE).build();
        when(repository.findById(1L)).thenReturn(Optional.of(persisted));

        MembroResponse result = service.buscar(1L);

        assertThat(result.nome()).isEqualTo("Maria");
        assertThat(result.cargo()).isEqualTo(CargoMembro.GERENTE);
    }

    @Test
    @DisplayName("buscar lança quando não existe")
    void buscarNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.buscar(99L))
                .isInstanceOf(RecursoNaoEncontradoException.class);
    }

    @Test
    @DisplayName("listar retorna todos os membros")
    void listar() {
        when(repository.findAll()).thenReturn(List.of(
                Membro.builder().nome("Maria").cargo(CargoMembro.FUNCIONARIO).build(),
                Membro.builder().nome("Bruno").cargo(CargoMembro.GERENTE).build()));

        List<MembroResponse> result = service.listar(null);

        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("listar filtra por cargo")
    void listarComCargo() {
        when(repository.findByCargo(CargoMembro.FUNCIONARIO)).thenReturn(List.of(
                Membro.builder().nome("Maria").cargo(CargoMembro.FUNCIONARIO).build()));

        List<MembroResponse> result = service.listar(CargoMembro.FUNCIONARIO);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).nome()).isEqualTo("Maria");
    }
}

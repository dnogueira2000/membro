package com.empresa.members.domain.model;

import com.empresa.members.enums.CargoMembro;
import com.empresa.members.model.Membro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MembroTest {

    @Test
    @DisplayName("builder cria membro com nome e cargo")
    void builder() {
        Membro m = Membro.builder()
                .nome("Maria")
                .cargo(CargoMembro.FUNCIONARIO)
                .build();
        assertThat(m.getNome()).isEqualTo("Maria");
        assertThat(m.getCargo()).isEqualTo(CargoMembro.FUNCIONARIO);
    }

    @Test
    @DisplayName("setters alteram nome e cargo")
    void setters() {
        Membro m = Membro.builder()
                .nome("Maria")
                .cargo(CargoMembro.FUNCIONARIO)
                .build();
        m.setNome("Maria Silva");
        m.setCargo(CargoMembro.GERENTE);
        assertThat(m.getNome()).isEqualTo("Maria Silva");
        assertThat(m.getCargo()).isEqualTo(CargoMembro.GERENTE);
    }
}

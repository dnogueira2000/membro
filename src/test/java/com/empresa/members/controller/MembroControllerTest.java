package com.empresa.members.controller;

import com.empresa.members.enums.CargoMembro;
import com.empresa.members.config.exception.RecursoNaoEncontradoException;
import com.empresa.members.dto.response.MembroResponse;
import com.empresa.members.service.MembroService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MembroController.class)
class MembroControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean   private MembroService service;

    @Test
    @DisplayName("POST /api/membros cria membro e retorna 201")
    void criar() throws Exception {
        when(service.criar(any())).thenReturn(new MembroResponse(1L, "Maria", CargoMembro.FUNCIONARIO));

        String body = """
                { "nome": "Maria", "cargo": "FUNCIONARIO" }
                """;

        mvc.perform(post("/api/membros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cargo").value("FUNCIONARIO"));
    }

    @Test
    @DisplayName("POST /api/membros valida payload obrigatório")
    void criarInvalido() throws Exception {
        mvc.perform(post("/api/membros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /api/membros/{id} retorna 404 quando não existe")
    void buscarNaoEncontrado() throws Exception {
        when(service.buscar(99L)).thenThrow(new RecursoNaoEncontradoException("Membro", 99L));

        mvc.perform(get("/api/membros/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/membros/{id} retorna membro existente")
    void buscar() throws Exception {
        when(service.buscar(1L)).thenReturn(new MembroResponse(1L, "Maria", CargoMembro.GERENTE));

        mvc.perform(get("/api/membros/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Maria"))
                .andExpect(jsonPath("$.cargo").value("GERENTE"));
    }

    @Test
    @DisplayName("GET /api/membros lista todos")
    void listar() throws Exception {
        when(service.listar(null)).thenReturn(List.of(
                new MembroResponse(1L, "Ana", CargoMembro.GERENTE),
                new MembroResponse(2L, "Bruno", CargoMembro.FUNCIONARIO)));

        mvc.perform(get("/api/membros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].cargo").value("FUNCIONARIO"));
    }

    @Test
    @DisplayName("GET /api/membros filtra por cargo")
    void listarComCargo() throws Exception {
        when(service.listar(CargoMembro.FUNCIONARIO)).thenReturn(List.of(
                new MembroResponse(2L, "Bruno", CargoMembro.FUNCIONARIO)));

        mvc.perform(get("/api/membros").param("cargo", "FUNCIONARIO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].cargo").value("FUNCIONARIO"));
    }
}

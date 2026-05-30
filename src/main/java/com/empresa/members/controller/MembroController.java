package com.empresa.members.controller;

import com.empresa.members.enums.CargoMembro;
import com.empresa.members.dto.request.MembroRequest;
import com.empresa.members.dto.response.MembroResponse;
import com.empresa.members.service.MembroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/membros")
@Tag(name = "Membros", description = "Cadastro e consulta de membros")
public class MembroController {

    private final MembroService service;

    public MembroController(MembroService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo membro")
    public ResponseEntity<MembroResponse> criar(@Valid @RequestBody MembroRequest request) {
        MembroResponse criado = service.criar(request);
        return ResponseEntity.created(URI.create("/api/membros/" + criado.id())).body(criado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um membro por id")
    public ResponseEntity<MembroResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @GetMapping
    @Operation(summary = "Lista todos os membros")
    public ResponseEntity<List<MembroResponse>> listar(@RequestParam(value = "cargo", required = false) CargoMembro cargo) {
        return ResponseEntity.ok(service.listar(cargo));
    }
}

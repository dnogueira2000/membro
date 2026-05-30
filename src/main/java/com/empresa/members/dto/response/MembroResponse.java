package com.empresa.members.dto.response;

import com.empresa.members.enums.CargoMembro;

public record MembroResponse(Long id, String nome, CargoMembro cargo) { }

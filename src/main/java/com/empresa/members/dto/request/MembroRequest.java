package com.empresa.members.dto.request;

import com.empresa.members.enums.CargoMembro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MembroRequest(
        @NotBlank @Size(max = 150) String nome,
        @NotNull CargoMembro cargo
) { }

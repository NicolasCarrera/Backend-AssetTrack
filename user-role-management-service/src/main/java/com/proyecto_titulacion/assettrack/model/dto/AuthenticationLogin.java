package com.proyecto_titulacion.assettrack.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationLogin(@NotBlank String identification, @NotBlank String password) {
}

package com.proyecto_titulacion.assettrack.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateUser(@NotBlank String username, @NotBlank String password, List<String> roles) {
}

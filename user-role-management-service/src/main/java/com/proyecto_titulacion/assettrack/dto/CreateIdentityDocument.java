package com.proyecto_titulacion.assettrack.dto;

import com.proyecto_titulacion.assettrack.model.DocumentType;
import jakarta.validation.constraints.NotBlank;

public record CreateIdentityDocument(@NotBlank DocumentType documentType, @NotBlank String identification) {
}

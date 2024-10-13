package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.type.StatusType;

public record CreateCompany(
        String name,
        Long userId,
        StatusType status
) {
}

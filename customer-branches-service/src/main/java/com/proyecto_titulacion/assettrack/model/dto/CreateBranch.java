package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.type.StatusType;

public record CreateBranch(
        String name,
        String location,
        String email,
        String phone,
        StatusType status,
        Long companyId
) {
}

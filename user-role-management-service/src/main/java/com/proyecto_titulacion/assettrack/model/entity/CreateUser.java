package com.proyecto_titulacion.assettrack.model.entity;

import com.proyecto_titulacion.assettrack.model.dto.IdentityDocumentDTO;
import com.proyecto_titulacion.assettrack.model.type.StatusType;

import java.util.List;

public record CreateUser(
        Long id,
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        List<IdentityDocumentDTO> documents,
        String role,
        StatusType status
) {
}

package com.proyecto_titulacion.assettrack.model.dto;

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
        IdentityDocumentDTO document,
        String role,
        StatusType status
) {
}

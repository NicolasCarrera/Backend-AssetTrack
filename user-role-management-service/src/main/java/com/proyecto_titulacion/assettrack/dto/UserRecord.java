package com.proyecto_titulacion.assettrack.dto;

import com.proyecto_titulacion.assettrack.model.Status;

import java.util.Set;

public record UserRecord(Long id, String username, Set<RoleRecord> roles, Status status) {
}

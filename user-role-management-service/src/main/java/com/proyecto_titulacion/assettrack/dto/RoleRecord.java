package com.proyecto_titulacion.assettrack.dto;

import java.util.Set;

public record RoleRecord(Long id, String roleName, Set<PermissionRecord> permissions) {
}

package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.CreatePermission;
import com.proyecto_titulacion.assettrack.dto.PermissionRecord;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    PermissionRecord savePermission(CreatePermission permission);
    PermissionRecord updatePermission (Long id, PermissionRecord permissionDetails);
    Set<PermissionRecord> getPermitsByPermitsNames (List<String> permitsNames);
}

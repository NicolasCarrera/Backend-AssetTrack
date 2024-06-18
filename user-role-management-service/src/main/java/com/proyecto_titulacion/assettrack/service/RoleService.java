package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.CreateRole;
import com.proyecto_titulacion.assettrack.dto.RoleRecord;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<RoleRecord> getAllRoles();
    Set<RoleRecord> getRolesByRoleName(List<String> roleName);
    RoleRecord saveRole(CreateRole role);
    RoleRecord updateRole(Long id, RoleRecord roleDetails);
}

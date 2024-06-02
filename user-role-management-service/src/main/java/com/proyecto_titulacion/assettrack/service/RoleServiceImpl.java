package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.CreateRole;
import com.proyecto_titulacion.assettrack.dto.PermissionRecord;
import com.proyecto_titulacion.assettrack.model.PermissionEntity;
import com.proyecto_titulacion.assettrack.model.RoleEntity;
import com.proyecto_titulacion.assettrack.util.PermissionUtil;
import com.proyecto_titulacion.assettrack.util.RoleUtil;
import com.proyecto_titulacion.assettrack.dto.RoleRecord;
import com.proyecto_titulacion.assettrack.repository.RoleRepository;
import com.proyecto_titulacion.assettrack.util.UserUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionService permissionService;

    @Override
    public Set<RoleRecord> getRolesByRoleName(List<String> roleName) {
        Set<RoleEntity> roleEntities = this.roleRepository.findRolesByRoleName(roleName);
        return roleEntities.stream().map(RoleUtil::toRecord).collect(Collectors.toSet());
    }

    @Override
    public RoleRecord saveRole(CreateRole role) {
        RoleEntity newRole = new RoleEntity();
        newRole.setRoleName(role.roleName());

        Set<PermissionRecord> permits = this.permissionService.getPermitsByPermitsNames(role.permissions());

        newRole.setPermissions(permits.stream().map(PermissionUtil::toEntity).collect(Collectors.toSet()));

        return RoleUtil.toRecord(this.roleRepository.save(newRole));
    }

    @Override
    public RoleRecord updateRole(Long id, RoleRecord roleDetails) {
        RoleEntity role = this.roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con ID: " + id));
        role.setRoleName(roleDetails.roleName());

        role.getPermissions().clear();
        role.setPermissions(roleDetails.permissions().stream().map(PermissionUtil::toEntity).collect(Collectors.toSet()));

        return RoleUtil.toRecord(this.roleRepository.save(role));
    }
}

package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.CreatePermission;
import com.proyecto_titulacion.assettrack.model.PermissionEntity;
import com.proyecto_titulacion.assettrack.util.PermissionUtil;
import com.proyecto_titulacion.assettrack.dto.PermissionRecord;
import com.proyecto_titulacion.assettrack.repository.PermissionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public PermissionRecord savePermission(CreatePermission permission) {
        PermissionEntity newPermission = new PermissionEntity();
        newPermission.setPermissionName(permission.permissionName());

        return PermissionUtil.toRecord(this.permissionRepository.save(newPermission));
    }

    @Override
    public Set<PermissionRecord> getPermitsByPermitsNames(List<String> permitsNames) {
        Set<PermissionEntity> permissionEntities = this.permissionRepository.findPermitsByPermitsNames(permitsNames);
        return permissionEntities.stream().map(PermissionUtil::toRecord).collect(Collectors.toSet());
    }

    @Override
    public PermissionRecord updatePermission(Long id, PermissionRecord permissionDetails) {
        PermissionEntity permission = this.permissionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Permiso no encontrado con ID: " + id));
        permission.setPermissionName(permissionDetails.permissionName());

        return PermissionUtil.toRecord(this.permissionRepository.save(permission));
    }

}

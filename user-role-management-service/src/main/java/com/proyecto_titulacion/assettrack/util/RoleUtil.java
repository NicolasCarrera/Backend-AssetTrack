package com.proyecto_titulacion.assettrack.util;

import com.proyecto_titulacion.assettrack.dto.RoleRecord;
import com.proyecto_titulacion.assettrack.model.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleUtil {
    public static RoleRecord toRecord(RoleEntity entity) {
        return new RoleRecord(
                entity.getId(),
                entity.getRoleName(),
                entity.getPermissions().stream().map(PermissionUtil::toRecord).collect(Collectors.toSet())
        );
    }

    public static RoleEntity toEntity(RoleRecord record) {
        RoleEntity entity = new RoleEntity();
        entity.setId(record.id());
        entity.setRoleName(record.roleName());
        entity.setPermissions(record.permissions().stream().map(PermissionUtil::toEntity).collect(Collectors.toSet()));
        return entity;
    }
}

package com.proyecto_titulacion.assettrack.util;

import com.proyecto_titulacion.assettrack.dto.PermissionRecord;
import com.proyecto_titulacion.assettrack.model.PermissionEntity;
import org.springframework.stereotype.Component;

@Component
public class PermissionUtil {
    public static PermissionRecord toRecord(PermissionEntity entity) {
        return new PermissionRecord(
                entity.getId(),
                entity.getPermissionName()
        );
    }
    public static PermissionEntity toEntity(PermissionRecord record) {
        PermissionEntity entity = new PermissionEntity();
        entity.setId(record.id());
        entity.setPermissionName(record.permissionName());
        return entity;
    }
}

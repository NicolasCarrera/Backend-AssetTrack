package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.entity.PermissionEntity;

public record PermissionDTO(
        Long id,
        String name
) {
    public static PermissionDTO toPermissionDTO(PermissionEntity permission) {
        return new PermissionDTO(
                permission.getId(),
                permission.getName()
        );
    }

    public PermissionEntity toPermissionEntity() {
        return PermissionEntity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}

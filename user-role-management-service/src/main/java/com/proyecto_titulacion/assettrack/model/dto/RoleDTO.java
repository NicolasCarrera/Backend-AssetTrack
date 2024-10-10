package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.entity.RoleEntity;

import java.util.Set;
import java.util.stream.Collectors;

public record RoleDTO(
        Long id,
        String name,
        Set<PermissionDTO> permissions
) {
    public static RoleDTO toRoleDTO(RoleEntity roleEntity) {
        return new RoleDTO(
                roleEntity.getId(),
                roleEntity.getName(),
                roleEntity.getPermissions().stream().map(PermissionDTO::toPermissionDTO).collect(Collectors.toSet())
        );
    }

    public RoleEntity toRoleEntity() {
        return RoleEntity.builder()
                .id(this.id)
                .name(this.name)
                .permissions(this.permissions.stream().map(PermissionDTO::toPermissionEntity).collect(Collectors.toSet()))
                .build();
    }
}

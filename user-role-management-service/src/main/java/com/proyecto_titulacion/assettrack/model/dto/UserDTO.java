package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.entity.UserEntity;
import com.proyecto_titulacion.assettrack.model.type.StatusType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        List<IdentityDocumentDTO> documents,
        Set<RoleDTO> roles,
        StatusType status
) {
    public static UserDTO toUserDTO(UserEntity user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getIdentityDocuments().stream().map(IdentityDocumentDTO::toIdentityDocumentDTO).toList(),
                user.getRoles().stream().map(RoleDTO::toRoleDTO).collect(Collectors.toSet()),
                user.getStatus()
        );
    }

    public UserEntity toUserEntity(String password) {
        return UserEntity.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(password)
                .phone(this.phone)
                .identityDocuments(this.documents.stream().map(IdentityDocumentDTO::toIdentityDocument).toList())
                .roles(this.roles.stream().map(RoleDTO::toRoleEntity).collect(Collectors.toSet()))
                .status(this.status)
                .build();
    }
}

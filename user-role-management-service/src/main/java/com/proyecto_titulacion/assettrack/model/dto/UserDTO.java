package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.entity.UserEntity;
import com.proyecto_titulacion.assettrack.model.type.StatusType;

import java.util.stream.Collectors;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        IdentityDocumentDTO documents,
        RoleDTO roles,
        StatusType status
) {
    public static UserDTO toUserDTO(UserEntity user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                IdentityDocumentDTO.toIdentityDocumentDTO(user.getIdentityDocuments()),
                RoleDTO.toRoleDTO(user.getRole()),
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
                .identityDocuments(this.documents.toIdentityDocument())
                .role(this.roles.toRoleEntity())
                .status(this.status)
                .build();
    }
}

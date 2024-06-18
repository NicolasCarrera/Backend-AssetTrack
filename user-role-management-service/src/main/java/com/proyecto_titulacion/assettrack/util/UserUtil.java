package com.proyecto_titulacion.assettrack.util;

import com.proyecto_titulacion.assettrack.dto.UserRecord;
import com.proyecto_titulacion.assettrack.model.UserEntity;

import java.util.HashSet;
import java.util.stream.Collectors;

public class UserUtil {
    public static UserRecord toRecord(UserEntity entity) {
        return new UserRecord(
                entity.getId(),
                entity.getNames(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getIdentityDocuments().stream().map(IdentityDocumentUtil::toRecord).toList(),
                entity.getRoles().stream().map(RoleUtil::toRecord).collect(Collectors.toSet()),
                entity.getStatus()
        );
    }

    public static UserEntity toEntity(UserRecord record) {
        UserEntity entity = new UserEntity();
        entity.setId(record.id());
        entity.setPassword("***************");
        entity.setNames(record.names());
        entity.setLastName(record.lastName());
        entity.setEmail(record.email());
        entity.setPhoneNumber(record.phoneNumber());
        entity.setIdentityDocuments(record.identityDocuments().stream().map(IdentityDocumentUtil::toEntity).toList());
        entity.setRoles(record.roles().stream().map(RoleUtil::toEntity).collect(Collectors.toSet()));
        entity.setStatus(record.status());
        return entity;
    }
}

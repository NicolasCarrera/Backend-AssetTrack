package com.proyecto_titulacion.assettrack.dto;

import com.proyecto_titulacion.assettrack.model.IdentityDocument;
import com.proyecto_titulacion.assettrack.model.Status;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

import java.util.List;
import java.util.Set;

public record UserRecord(Long id, String names, String lastName, String email, String phoneNumber, List<IdentityDocumentRecord> identityDocuments, Set<RoleRecord> roles, Status status) {
}

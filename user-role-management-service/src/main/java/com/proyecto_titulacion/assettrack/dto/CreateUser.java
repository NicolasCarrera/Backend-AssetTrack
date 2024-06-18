package com.proyecto_titulacion.assettrack.dto;

import com.proyecto_titulacion.assettrack.model.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateUser(Long id, @NotBlank String password, @NotBlank String names, String lastName, @NotBlank String email, String phoneNumber, @Valid List<CreateIdentityDocument> identityDocuments, List<String> roles, Status status) {
}

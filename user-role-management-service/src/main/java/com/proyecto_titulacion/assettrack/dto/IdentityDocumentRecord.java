package com.proyecto_titulacion.assettrack.dto;

import com.proyecto_titulacion.assettrack.model.DocumentType;

public record IdentityDocumentRecord(Long id, DocumentType documentType, String identification) {
}

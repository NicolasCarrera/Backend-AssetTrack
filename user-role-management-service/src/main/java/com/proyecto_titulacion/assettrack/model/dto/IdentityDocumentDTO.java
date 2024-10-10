package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.entity.IdentityDocument;
import com.proyecto_titulacion.assettrack.model.type.DocumentType;

public record IdentityDocumentDTO(
        Long id,
        DocumentType document,
        String identification
) {
    public static IdentityDocumentDTO toIdentityDocumentDTO(IdentityDocument identityDocument) {
        return new IdentityDocumentDTO(
                identityDocument.getId(),
                identityDocument.getDocument(),
                identityDocument.getIdentification()
        );
    }

    public IdentityDocument toIdentityDocument() {
        return IdentityDocument.builder()
                .id(this.id)
                .document(this.document)
                .identification(this.identification)
                .build();
    }
}

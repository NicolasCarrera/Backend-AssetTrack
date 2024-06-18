package com.proyecto_titulacion.assettrack.util;

import com.proyecto_titulacion.assettrack.dto.IdentityDocumentRecord;
import com.proyecto_titulacion.assettrack.model.IdentityDocument;

public class IdentityDocumentUtil {
    public static IdentityDocumentRecord toRecord(IdentityDocument entity) {
        return new IdentityDocumentRecord(
                entity.getId(),
                entity.getDocumentType(),
                entity.getIdentification()
        );
    }

    public static IdentityDocument toEntity(IdentityDocumentRecord record) {
        IdentityDocument identityDocument = new IdentityDocument();
        identityDocument.setId(record.id());
        identityDocument.setDocumentType(record.documentType());
        identityDocument.setIdentification(record.identification());
        return identityDocument;
    }
}

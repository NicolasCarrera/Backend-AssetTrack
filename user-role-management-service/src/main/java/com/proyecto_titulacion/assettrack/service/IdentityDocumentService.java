package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.CreateIdentityDocument;
import com.proyecto_titulacion.assettrack.dto.CreateRole;
import com.proyecto_titulacion.assettrack.dto.IdentityDocumentRecord;
import com.proyecto_titulacion.assettrack.dto.RoleRecord;

import java.util.List;

public interface IdentityDocumentService {
    List<IdentityDocumentRecord> getAllIdentityDocument();
    IdentityDocumentRecord saveRole(CreateIdentityDocument identityDocument);
    IdentityDocumentRecord updateRole(Long id, IdentityDocumentRecord identityDocumentDetails);
}

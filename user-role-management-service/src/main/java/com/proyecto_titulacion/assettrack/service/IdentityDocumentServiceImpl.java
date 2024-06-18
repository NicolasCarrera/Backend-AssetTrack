package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.CreateIdentityDocument;
import com.proyecto_titulacion.assettrack.dto.IdentityDocumentRecord;
import com.proyecto_titulacion.assettrack.model.IdentityDocument;
import com.proyecto_titulacion.assettrack.repository.IdentityDocumentRepository;
import com.proyecto_titulacion.assettrack.util.IdentityDocumentUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentityDocumentServiceImpl implements IdentityDocumentService {
    @Autowired
    private IdentityDocumentRepository identityDocumentRepository;
    @Override
    public List<IdentityDocumentRecord> getAllIdentityDocument() {
        List<IdentityDocument> identityDocuments = this.identityDocumentRepository.findAll();
        return identityDocuments.stream().map(IdentityDocumentUtil::toRecord).toList();
    }

    @Override
    public IdentityDocumentRecord saveRole(CreateIdentityDocument identityDocument) {
        IdentityDocument newIdentityDocument = new IdentityDocument();
        newIdentityDocument.setDocumentType(identityDocument.documentType());
        newIdentityDocument.setIdentification(identityDocument.identification());

        return IdentityDocumentUtil.toRecord(this.identityDocumentRepository.save(newIdentityDocument));
    }

    @Override
    public IdentityDocumentRecord updateRole(Long id, IdentityDocumentRecord identityDocumentDetails) {
        IdentityDocument identityDocument = this.identityDocumentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Identificación no encontrado con ID: " + id));
        identityDocument.setDocumentType(identityDocumentDetails.documentType());
        identityDocument.setIdentification(identityDocumentDetails.identification());

        return IdentityDocumentUtil.toRecord(this.identityDocumentRepository.save(identityDocument));
    }
}

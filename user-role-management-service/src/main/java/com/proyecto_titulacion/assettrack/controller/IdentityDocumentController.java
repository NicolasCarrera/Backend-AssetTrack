package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.dto.IdentityDocumentRecord;
import com.proyecto_titulacion.assettrack.service.IdentityDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/identity-document")
@CrossOrigin(origins = "*")
public class IdentityDocumentController {
    @Autowired
    private IdentityDocumentService identityDocumentService;

    @GetMapping
    public ResponseEntity<List<IdentityDocumentRecord>> getAllIdentityDocument() {
        try {
            List<IdentityDocumentRecord> identityDocumentRecords = this.identityDocumentService.getAllIdentityDocument();
            if (!identityDocumentRecords.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(identityDocumentRecords);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

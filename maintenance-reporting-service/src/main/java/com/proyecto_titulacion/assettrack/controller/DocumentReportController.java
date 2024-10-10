package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.service.DocumentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document-report")
@CrossOrigin(origins = "*")
public class DocumentReportController {
    @Autowired
    private DocumentReportService documentReportService;
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadReport(@PathVariable("id") Long id) {
        // Llamar al servicio para generar el PDF
        byte[] pdfBytes = this.documentReportService.generateReport(id);

        // Crear las cabeceras para la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "reporte.pdf");

        // Retornar el archivo PDF
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}

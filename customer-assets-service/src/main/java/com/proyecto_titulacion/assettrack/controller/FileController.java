package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*")
@Tag(name = "Descargar archivo", description = "API para gestionar la descarga de archivos")
public class FileController {
    @Autowired
    private FileService fileService;

    @Operation(summary = "Descargar un archivo",
            description = "Permite descargar un archivo desde el servidor según la ruta especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Archivo descargado exitosamente",
                    content = @Content(schema = @Schema(implementation = Resource.class))),
            @ApiResponse(responseCode = "404",
                    description = "Archivo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/{*filePath}")
    public ResponseEntity<Resource> getFile(
            @Parameter(
                    description = "Ruta relativa del archivo que se va a descargar",
                    required = true,
                    example = "documents/file.pdf"
            )
            @PathVariable("filePath") String filePath
    ) throws Exception {
        String decodedFilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);

        InputStream fileStream = this.fileService.getFile(decodedFilePath);
        InputStreamResource resource = new InputStreamResource(fileStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + getFileName(decodedFilePath) + "\"");

        MediaType mediaType = getMediaTypeForFileName(decodedFilePath);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(mediaType)
                .body(resource);
    }

    private String getFileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf('/') + 1);
    }

    private MediaType getMediaTypeForFileName(String fileName) {
        String[] parts = fileName.split("\\.");
        String extension = parts[parts.length - 1].toLowerCase();
        return switch (extension) {
            case "pdf" -> MediaType.APPLICATION_PDF;
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }
}

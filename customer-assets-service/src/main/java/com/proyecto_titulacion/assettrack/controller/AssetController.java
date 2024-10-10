package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.model.dto.AssetDTO;
import com.proyecto_titulacion.assettrack.service.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Gestión de activos", description = "API para la gestión de activos")
public class AssetController {
    @Autowired
    private AssetService assetService;

    @Operation(summary = "Obtener activos en mantenimiento por Branch ID", description = "Devuelve una lista paginada de activos que están en mantenimiento en una sucursal específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activos encontrados con éxito",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))
            ),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/branch/{branchId}/maintenance")
    public ResponseEntity<Page<AssetDTO>> getAssetsInMaintenanceByBranchId(
            @PathVariable("branchId") Long branchId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<AssetDTO> assets = assetService.getAssetsInMaintenanceByBranchId(branchId, page, size);
        return ResponseEntity.ok(assets);
    }

    @Operation(summary = "Obtener activos por Branch ID", description = "Devuelve una lista paginada de activos de una sucursal específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activos encontrados con éxito"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<Page<AssetDTO>> getAssetsByBranchId(
            @PathVariable("branchId") Long branchId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<AssetDTO> assets = this.assetService.getAssetsByBranchId(branchId, page, size);
        return ResponseEntity.ok(assets);
    }

    @Operation(summary = "Obtener activos filtrados", description = "Devuelve una lista paginada de activos que coinciden con un filtro específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activos encontrados con éxito")
    })
    @GetMapping
    public ResponseEntity<Page<AssetDTO>> getAssetsByFilter(
            @RequestParam("filter") String filter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<AssetDTO> assets = assetService.getAssetsByFilter(filter, page, size);
        return ResponseEntity.ok(assets);
    }

    @Operation(summary = "Obtener un activo por ID", description = "Devuelve los detalles de un activo específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activo encontrado con éxito"),
            @ApiResponse(responseCode = "404", description = "Activo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AssetDTO> getAssetById(@PathVariable("id") Long id
    ) {
        try {
            AssetDTO asset = this.assetService.getAssetById(id);
            return ResponseEntity.ok(asset);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Crear un nuevo activo", description = "Crea un nuevo activo en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activo creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<AssetDTO> createAsset(@RequestBody AssetDTO createAsset) {
        AssetDTO createdAsset = assetService.createAsset(createAsset.toAsset());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAsset);
    }

    @Operation(summary = "Actualizar archivos de un activo",
            description = "Actualiza la imagen y los documentos asociados a un activo existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Archivos actualizados exitosamente",
                    content = @Content(schema = @Schema(implementation = AssetDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping("/{id}/files")
    public ResponseEntity<AssetDTO> updateAssetFiles(
            @Parameter(description = "ID del activo a actualizar", required = true)
            @PathVariable("id") Long assetId,
            @Parameter(description = "Imagen del activo actualizada")
            @RequestParam("image") MultipartFile image,
            @Parameter(description = "Lista de documentos actualizados asociados al activo")
            @RequestParam("documents") List<MultipartFile> documents
    ) {
        try {
            AssetDTO updatedAsset = this.assetService.updateAssetFiles(assetId, image, documents);
            return ResponseEntity.ok(updatedAsset);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Actualizar un activo por ID", description = "Actualiza los detalles de un activo existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activo actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Activo no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AssetDTO> updateAsset(
            @PathVariable("id") Long id,
            @RequestBody AssetDTO asset
    ) {
        try {
            AssetDTO updatedAsset = assetService.updateAsset(id, asset.toAsset());
            return ResponseEntity.ok(updatedAsset);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Eliminar un activo por ID", description = "Elimina un activo específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Activo eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Activo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable("id") Long id) {
        try {
            assetService.deleteAsset(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Contar activos por Branch ID", description = "Devuelve el número de activos en una sucursal específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conteo exitoso")
    })
    @GetMapping("/branch/{branchId}/count")
    public ResponseEntity<Integer> countAssetsByBranchId(@PathVariable("branchId") Long branchId) {
        Integer count = assetService.countAssetsByBranchId(branchId);
        return ResponseEntity.ok(count);
    }
}

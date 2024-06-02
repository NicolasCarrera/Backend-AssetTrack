package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.dto.CreatePermission;
import com.proyecto_titulacion.assettrack.dto.PermissionRecord;
import com.proyecto_titulacion.assettrack.service.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ResponseEntity<PermissionRecord> createPermission(@RequestBody CreatePermission permission) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.permissionService.savePermission(permission));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<PermissionRecord> updatePermission(@PathVariable Long id, @RequestBody PermissionRecord permissionDetails) {
        try {
            PermissionRecord updatePermission = this.permissionService.updatePermission(id, permissionDetails);
            return ResponseEntity.ok(updatePermission);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

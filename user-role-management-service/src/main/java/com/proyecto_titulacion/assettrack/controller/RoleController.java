package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.dto.CreateRole;
import com.proyecto_titulacion.assettrack.dto.RoleRecord;
import com.proyecto_titulacion.assettrack.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleRecord> createRole(@RequestBody CreateRole role){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.saveRole(role));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<RoleRecord> updateRole(@PathVariable Long id, @RequestBody RoleRecord roleDetails) {
        try {
            RoleRecord updateRole = this.roleService.updateRole(id, roleDetails);
            return ResponseEntity.ok(updateRole);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
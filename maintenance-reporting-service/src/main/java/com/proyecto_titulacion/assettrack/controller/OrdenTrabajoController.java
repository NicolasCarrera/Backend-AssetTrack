package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.model.OrdenTrabajo;
import com.proyecto_titulacion.assettrack.service.OrdenTrabajoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orden-trabajo")
public class OrdenTrabajoController {
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @GetMapping
    public ResponseEntity<Page<OrdenTrabajo>> getAllOrdenesTrabajo(@RequestParam(name = "page", required = false ) int page, @RequestParam(name = "size", required = false) int size){
        try {
            Page<OrdenTrabajo> ordenTrabajoPage = this.ordenTrabajoService.getAllOrdenesTrabajo(page, size);
            if (!ordenTrabajoPage.hasContent()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(ordenTrabajoPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> getUserById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(this.ordenTrabajoService.getOrdenesTrabajoById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<OrdenTrabajo> createOrdenTrabajo(@RequestBody OrdenTrabajo create) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.ordenTrabajoService.createOrdenTrabajo(create));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> updateOrdenTrabajo(@PathVariable("id") Long id, @RequestBody OrdenTrabajo ordenTrabajo){
        try {
            OrdenTrabajo updatePrdenTrabajo = this.ordenTrabajoService.updateOrdenTrabajo(id, ordenTrabajo);
            return ResponseEntity.ok(updatePrdenTrabajo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> deleteOrdenTrabajo(@PathVariable("id") Long id){
        try {
            OrdenTrabajo deleteOrdenTrabajo = this.ordenTrabajoService.deleteOrdenTrabajo(id);
            return ResponseEntity.ok(deleteOrdenTrabajo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

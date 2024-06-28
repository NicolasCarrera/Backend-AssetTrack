package com.proyecto_titulacion.assettrack.controller;


import com.proyecto_titulacion.assettrack.client.ClienteService;
import com.proyecto_titulacion.assettrack.dto.ActivoDTO;
import com.proyecto_titulacion.assettrack.dto.SucursalDTO;
import com.proyecto_titulacion.assettrack.service.ActivoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/activos")
public class ActivoController {

    @Autowired
    private ActivoServiceImpl activoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/test/{id}")
    public SucursalDTO test(@PathVariable(value = "id") Long id) {
        return clienteService.getSucursalById(id).block();
    }

    @GetMapping
    public List<ActivoDTO> getAllActivos() {
        return activoService.getAllActivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivoDTO> getActivoById(@PathVariable(value = "id") Long id) {
        ActivoDTO activoDTO = activoService.getActivoById(id);
        return ResponseEntity.ok().body(activoDTO);
    }

    @PostMapping
    public ActivoDTO createActivo(@RequestBody ActivoDTO activoDTO) {
        return activoService.createActivo(activoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivoDTO> updateActivo(@PathVariable(value = "id") Long id, @RequestBody ActivoDTO activoDTO) {
        ActivoDTO updatedActivoDTO = activoService.updateActivo(id, activoDTO);
        return ResponseEntity.ok(updatedActivoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivo(@PathVariable(value = "id") Long id) {
        activoService.deleteActivo(id);
        return ResponseEntity.noContent().build();
    }

}

package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.dto.SucursalDTO;
import com.proyecto_titulacion.assettrack.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> getAllSucursales() {
        List<SucursalDTO> sucursales = sucursalService.getAllSucursales();
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> getSucursalById(@PathVariable("id") Long id) {
        SucursalDTO sucursal = sucursalService.getSucursalById(id);
        return new ResponseEntity<>(sucursal, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> createSucursal(@RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO nuevaSucursal = sucursalService.createSucursal(sucursalDTO);
        return new ResponseEntity<>(nuevaSucursal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> updateSucursal(@PathVariable("id") Long id, @RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO sucursalActualizada = sucursalService.updateSucursal(id, sucursalDTO);
        return new ResponseEntity<>(sucursalActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable("id") Long id) {
        sucursalService.deleteSucursal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

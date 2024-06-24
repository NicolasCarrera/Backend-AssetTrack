package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.dto.EmpresaDTO;
import com.proyecto_titulacion.assettrack.model.Empresa;
import com.proyecto_titulacion.assettrack.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> getAllEmpresas() {
        List<EmpresaDTO> empresas = empresaService.getAllEmpresas();
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> getEmpresaById(@PathVariable Long id) {
        EmpresaDTO empresa = empresaService.getEmpresaById(id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> createEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO nuevaEmpresa = empresaService.createEmpresa(empresaDTO);
        return new ResponseEntity<>(nuevaEmpresa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> updateEmpresa(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO empresaActualizada = empresaService.updateEmpresa(id, empresaDTO);
        return new ResponseEntity<>(empresaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        empresaService.deleteEmpresa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.EmpresaDTO;
import com.proyecto_titulacion.assettrack.exception.ResourceNotFoundException;
import com.proyecto_titulacion.assettrack.mapper.EmpresaMapper;
import com.proyecto_titulacion.assettrack.model.Empresa;
import com.proyecto_titulacion.assettrack.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmpresaServiceImpl implements  EmpresaService{

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaMapper empresaMapper;


    @Transactional(readOnly = true)
    @Override
    public List<EmpresaDTO> getAllEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas.stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public EmpresaDTO getEmpresaById(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa not found with id: " + id));
        return empresaMapper.toDTO(empresa);    }

    @Transactional
    @Override
    public EmpresaDTO createEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = empresaMapper.toEntity(empresaDTO);
        // Puedes agregar validaciones u otras lógicas de negocio aquí si es necesario
        Empresa nuevaEmpresa = empresaRepository.save(empresa);
        return empresaMapper.toDTO(nuevaEmpresa);
    }

    @Transactional
    @Override
    public EmpresaDTO updateEmpresa(Long id, EmpresaDTO empresaDTO) {
        if (!empresaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Empresa not found with id: " + id);
        }
        empresaDTO.setId(id); // Aseguramos que el ID del DTO sea el correcto
        Empresa empresa = empresaMapper.toEntity(empresaDTO);
        // Puedes agregar validaciones u otras lógicas de negocio aquí si es necesario
        Empresa empresaActualizada = empresaRepository.save(empresa);
        return empresaMapper.toDTO(empresaActualizada);
    }

    @Transactional
    @Override
    public void deleteEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa not found with id: " + id));
        empresaRepository.delete(empresa);
    }
}

package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.SucursalDTO;
import com.proyecto_titulacion.assettrack.exception.ResourceNotFoundException;
import com.proyecto_titulacion.assettrack.mapper.SucursalMapper;
import com.proyecto_titulacion.assettrack.model.Empresa;
import com.proyecto_titulacion.assettrack.model.Sucursal;
import com.proyecto_titulacion.assettrack.repository.EmpresaRepository;
import com.proyecto_titulacion.assettrack.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private SucursalMapper sucursalMapper;


    @Transactional(readOnly = true)
    @Override
    public List<SucursalDTO> getAllSucursales() {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        return sucursales.stream()
                .map(sucursalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public SucursalDTO getSucursalById(Long id) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal not found with id: " + id));
        return sucursalMapper.toDTO(sucursal);
    }

    @Transactional
    @Override
    public SucursalDTO createSucursal(SucursalDTO sucursalDTO) {
        // Validamos que la empresa exista
        Empresa empresa = empresaRepository.findById(sucursalDTO.getEmpresaId())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa not found with id: " + sucursalDTO.getEmpresaId()));

        Sucursal sucursal = sucursalMapper.toEntity(sucursalDTO);
        sucursal.setEmpresa(empresa); // Establecemos la relación con la empresa
        // Puedes agregar validaciones u otras lógicas de negocio aquí si es necesario
        Sucursal nuevaSucursal = sucursalRepository.save(sucursal);
        return sucursalMapper.toDTO(nuevaSucursal);    }

    @Transactional
    @Override
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDTO) {
        if (!sucursalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sucursal not found with id: " + id);
        }
        sucursalDTO.setId(id); // Aseguramos que el ID del DTO sea el correcto
        // Validamos que la empresa exista
        Empresa empresa = empresaRepository.findById(sucursalDTO.getEmpresaId())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa not found with id: " + sucursalDTO.getEmpresaId()));

        Sucursal sucursal = sucursalMapper.toEntity(sucursalDTO);
        sucursal.setEmpresa(empresa); // Establecemos la relación con la empresa
        // Puedes agregar validaciones u otras lógicas de negocio aquí si es necesario
        Sucursal sucursalActualizada = sucursalRepository.save(sucursal);
        return sucursalMapper.toDTO(sucursalActualizada);

    }

    @Transactional
    @Override
    public void deleteSucursal(Long id) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal not found with id: " + id));
        sucursalRepository.delete(sucursal);

    }
}

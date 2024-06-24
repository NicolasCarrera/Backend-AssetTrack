package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.ActivoDTO;
import com.proyecto_titulacion.assettrack.exception.ResourceNotFoundException;
import com.proyecto_titulacion.assettrack.model.Activo;
import com.proyecto_titulacion.assettrack.mapper.ActivoMapper;
import com.proyecto_titulacion.assettrack.repository.ActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivoServiceImpl implements ActivoService {

    @Autowired
    private ActivoRepository activoRepository;

    @Autowired
    private ActivoMapper activoMapper;

    @Override
    public List<ActivoDTO> getAllActivos() {
        return activoRepository.findAll().stream()
                .map(activoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ActivoDTO getActivoById(Long id) {
        Activo activo = activoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activo not found for this id :: " + id));
        return activoMapper.toDTO(activo);
    }

    @Override
    public ActivoDTO createActivo(ActivoDTO activoDTO) {
        Activo activo = activoMapper.toEntity(activoDTO);
        Activo nuevoActivo = activoRepository.save(activo);
        return activoMapper.toDTO(nuevoActivo);
    }

    @Override
    public void deleteActivo(Long id) {
        Activo activo = activoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activo not found for this id :: " + id));
        activoRepository.delete(activo);
    }

    @Override
    public ActivoDTO  updateActivo(Long id, ActivoDTO  activoDTO ) {
        Activo activo = activoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Activo not found for this id :: " + id));
        activo.setDescripcion(activoDTO.getDescripcion());
        activo.setSucursal((activoDTO.getSucursal()));
        activo.setArea(activoDTO.getArea());
        activo.setFechaProximoMantenimiento(activoDTO.getFechaProximoMantenimiento());
        activo.setFechaUltimoMantenimiento(activoDTO.getFechaUltimoMantenimiento());
        Activo activoActualizado = activoRepository.save(activo);

        return activoMapper.toDTO(activoActualizado);
    }

}

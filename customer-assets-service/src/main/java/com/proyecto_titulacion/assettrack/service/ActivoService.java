package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.ActivoDTO;
import com.proyecto_titulacion.assettrack.model.Activo;

import java.util.List;
import java.util.Optional;

public interface ActivoService {

    public List<ActivoDTO> getAllActivos();

    public ActivoDTO getActivoById(Long id);

    public ActivoDTO createActivo(ActivoDTO ActivoDTO);

    public void deleteActivo(Long id);

    public ActivoDTO  updateActivo(Long id, ActivoDTO  activoDTO );
}

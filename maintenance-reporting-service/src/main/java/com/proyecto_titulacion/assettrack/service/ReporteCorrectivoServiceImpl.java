package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.ReporteCorrectivoDTO;
import com.proyecto_titulacion.assettrack.exception.ResourceNotFoundException;
import com.proyecto_titulacion.assettrack.mapper.ReporteCorrectivoMapper;
import com.proyecto_titulacion.assettrack.model.Reporte;
import com.proyecto_titulacion.assettrack.model.ReporteCorrectivo;
import com.proyecto_titulacion.assettrack.model.ReportePreventivo;
import com.proyecto_titulacion.assettrack.repository.ReporteCorrectivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteCorrectivoServiceImpl implements ReporteCorrectivoService {

    @Autowired
    private ReporteCorrectivoRepository repository;

    @Autowired
    private ReporteCorrectivoMapper mapper;

    @Override
    public List<ReporteCorrectivoDTO> getAllReportes() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ReporteCorrectivoDTO getReporteById(Long id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("Reporte not found for this id :: " + id));
    }

    @Override
    public ReporteCorrectivoDTO createReporte(ReporteCorrectivoDTO dto) {
        ReporteCorrectivo reporte = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(reporte));
    }

    @Override
    public ReporteCorrectivoDTO updateReporte(Long id, ReporteCorrectivoDTO dto) {
        ReporteCorrectivo existingReporte = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reporte not found for this id :: " + id));
        updateEntityFromDTO(dto, existingReporte);
        return mapper.toDTO(repository.save(existingReporte));
    }

    @Override
    public void deleteReporte(Long id) {
        ReporteCorrectivo reporte = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reporte not found for this id :: " + id));
        repository.delete(reporte);
    }

    @Override
    public ReporteCorrectivoDTO getReporte(Reporte reporte) {
        ReporteCorrectivo correctivo = this.repository.getReporte(reporte);
        return mapper.toDTO(correctivo);
    }

    private void updateEntityFromDTO(ReporteCorrectivoDTO dto, ReporteCorrectivo entity) {
        entity.setFechaIncidente(dto.getFechaIncidente());
        entity.setDescripcionProblema(dto.getDescripcionProblema());
        entity.setAnalisisProblema(dto.getAnalisisProblema());
        entity.setCausaRaiz(dto.getCausaRaiz());
        entity.setFechaInicioReparacion(dto.getFechaInicioReparacion());
        entity.setFechaFinReparacion(dto.getFechaFinReparacion());
        entity.setDescripcionTrabajo(dto.getDescripcionTrabajo());
        entity.setPartesDanadas(dto.getPartesDanadas());
        entity.setPartesReemplazadas(dto.getPartesReemplazadas());
        entity.setEstadoDespuesReparacion(dto.getEstadoDespuesReparacion());
        entity.setTiempoInactividad(dto.getTiempoInactividad());
        entity.setImpactoOperacion(dto.getImpactoOperacion());
        entity.setRecomendacionesFuturas(dto.getRecomendacionesFuturas());
        entity.setTecnicoEncargado(dto.getTecnicoEncargado());
    }
}

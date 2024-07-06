package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.ReportePreventivoDTO;
import com.proyecto_titulacion.assettrack.exception.ResourceNotFoundException;
import com.proyecto_titulacion.assettrack.mapper.ReportePreventivoMapper;
import com.proyecto_titulacion.assettrack.model.Reporte;
import com.proyecto_titulacion.assettrack.model.ReportePreventivo;
import com.proyecto_titulacion.assettrack.repository.ReportePreventivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportePreventivoServiceImpl implements ReportePreventivoService {

    @Autowired
    private ReportePreventivoRepository repository;

    @Autowired
    private ReportePreventivoMapper mapper;

    @Override
    public List<ReportePreventivoDTO> getAllReportes() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ReportePreventivoDTO getReporteById(Long id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("Reporte not found for this id :: " + id));
    }

    @Override
    public ReportePreventivoDTO createReporte(ReportePreventivoDTO dto) {
        ReportePreventivo reporte = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(reporte));
    }

    @Override
    public ReportePreventivoDTO updateReporte(Long id, ReportePreventivoDTO dto) {
        ReportePreventivo existingReporte = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reporte not found for this id :: " + id));
        updateEntityFromDTO(dto, existingReporte);
        return mapper.toDTO(repository.save(existingReporte));
    }

    @Override
    public void deleteReporte(Long id) {
        ReportePreventivo reporte = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reporte not found for this id :: " + id));
        repository.delete(reporte);
    }

    @Override
    public ReportePreventivoDTO getReporte(Reporte reporte) {
        ReportePreventivo reportePreventivo = this.repository.getReporte(reporte);
        return mapper.toDTO(reportePreventivo);
    }

    private void updateEntityFromDTO(ReportePreventivoDTO dto, ReportePreventivo entity) {
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setDescripcionTrabajo(dto.getDescripcionTrabajo());
        entity.setPartesInspeccionadas(dto.getPartesInspeccionadas());
        entity.setPartesReemplazadas(dto.getPartesReemplazadas());
        entity.setResultadosInspeccion(dto.getResultadosInspeccion());
        entity.setObservaciones(dto.getObservaciones());
        entity.setTecnicoEncargado(dto.getTecnicoEncargado());
        entity.setRecomendacionesFuturas(dto.getRecomendacionesFuturas());
    }
}

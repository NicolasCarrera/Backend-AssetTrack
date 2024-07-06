package com.proyecto_titulacion.assettrack.mapper;

import com.proyecto_titulacion.assettrack.dto.ReportePreventivoDTO;
import com.proyecto_titulacion.assettrack.model.ReportePreventivo;
import org.springframework.stereotype.Component;

@Component
public class ReportePreventivoMapper {

    public ReportePreventivoDTO toDTO(ReportePreventivo entity) {
        ReportePreventivoDTO dto = new ReportePreventivoDTO();
        dto.setId(entity.getId());
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaFin(entity.getFechaFin());
        dto.setDescripcionTrabajo(entity.getDescripcionTrabajo());
        dto.setPartesInspeccionadas(entity.getPartesInspeccionadas());
        dto.setPartesReemplazadas(entity.getPartesReemplazadas());
        dto.setResultadosInspeccion(entity.getResultadosInspeccion());
        dto.setObservaciones(entity.getObservaciones());
        dto.setTecnicoEncargado(entity.getTecnicoEncargado());
        dto.setRecomendacionesFuturas(entity.getRecomendacionesFuturas());
        return dto;
    }

    public ReportePreventivo toEntity(ReportePreventivoDTO dto) {
        ReportePreventivo entity = new ReportePreventivo();
        entity.setId(dto.getId());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setDescripcionTrabajo(dto.getDescripcionTrabajo());
        entity.setPartesInspeccionadas(dto.getPartesInspeccionadas());
        entity.setPartesReemplazadas(dto.getPartesReemplazadas());
        entity.setResultadosInspeccion(dto.getResultadosInspeccion());
        entity.setObservaciones(dto.getObservaciones());
        entity.setTecnicoEncargado(dto.getTecnicoEncargado());
        entity.setRecomendacionesFuturas(dto.getRecomendacionesFuturas());
        return entity;
    }
}

package com.proyecto_titulacion.assettrack.mapper;

import com.proyecto_titulacion.assettrack.dto.ReporteCorrectivoDTO;
import com.proyecto_titulacion.assettrack.model.ReporteCorrectivo;
import org.springframework.stereotype.Component;

@Component
public class ReporteCorrectivoMapper {

    public ReporteCorrectivoDTO toDTO(ReporteCorrectivo entity) {
        ReporteCorrectivoDTO dto = new ReporteCorrectivoDTO();
        dto.setId(entity.getId());
        dto.setFechaIncidente(entity.getFechaIncidente());
        dto.setDescripcionProblema(entity.getDescripcionProblema());
        dto.setAnalisisProblema(entity.getAnalisisProblema());
        dto.setCausaRaiz(entity.getCausaRaiz());
        dto.setFechaInicioReparacion(entity.getFechaInicioReparacion());
        dto.setFechaFinReparacion(entity.getFechaFinReparacion());
        dto.setDescripcionTrabajo(entity.getDescripcionTrabajo());
        dto.setPartesDanadas(entity.getPartesDanadas());
        dto.setPartesReemplazadas(entity.getPartesReemplazadas());
        dto.setEstadoDespuesReparacion(entity.getEstadoDespuesReparacion());
        dto.setTiempoInactividad(entity.getTiempoInactividad());
        dto.setImpactoOperacion(entity.getImpactoOperacion());
        dto.setRecomendacionesFuturas(entity.getRecomendacionesFuturas());
        dto.setTecnicoEncargado(entity.getTecnicoEncargado());
        return dto;
    }

    public ReporteCorrectivo toEntity(ReporteCorrectivoDTO dto) {
        ReporteCorrectivo entity = new ReporteCorrectivo();
        entity.setId(dto.getId());
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
        return entity;
    }
}

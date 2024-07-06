package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.ReporteCorrectivoDTO;
import com.proyecto_titulacion.assettrack.model.Reporte;

import java.util.List;

public interface ReporteCorrectivoService {


    List<ReporteCorrectivoDTO> getAllReportes();

    ReporteCorrectivoDTO getReporteById(Long id);

    ReporteCorrectivoDTO createReporte(ReporteCorrectivoDTO dto);

    ReporteCorrectivoDTO updateReporte(Long id, ReporteCorrectivoDTO dto);

    void deleteReporte(Long id);

    ReporteCorrectivoDTO getReporte(Reporte reporte);
}

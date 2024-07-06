package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.Reporte;
import org.springframework.data.domain.Page;

public interface ReporteService {
    Page<Reporte> getAllReporte(int page, int size);
    Reporte getReporteById(Long id);
    Reporte createReporte(Reporte create);
    Reporte updateReporte(Long id, Reporte update);
    Reporte deleteReporte(Long id);
}

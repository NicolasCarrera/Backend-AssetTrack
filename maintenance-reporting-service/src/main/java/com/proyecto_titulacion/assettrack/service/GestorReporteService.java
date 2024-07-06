package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.ReporteCorrectivoDTO;
import com.proyecto_titulacion.assettrack.dto.ReportePreventivoDTO;
import com.proyecto_titulacion.assettrack.model.Reporte;
import com.proyecto_titulacion.assettrack.model.ReporteCorrectivo;
import com.proyecto_titulacion.assettrack.model.ReportePreventivo;

public interface GestorReporteService {
    Boolean createReportePreventivo(Long ordenTrabajoId, ReportePreventivo reportePreventivo);
    Boolean createReporteCorrectivo(Long ordenTrabajoId, ReporteCorrectivo reporteCorrectivo);
    ReportePreventivoDTO getReportePreventivo(Reporte reporte);
    ReporteCorrectivoDTO getReporteCorrectivo(Reporte reporte);
}

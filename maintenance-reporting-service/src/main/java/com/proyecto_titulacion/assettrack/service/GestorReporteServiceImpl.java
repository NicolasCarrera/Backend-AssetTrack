package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.ReporteCorrectivoDTO;
import com.proyecto_titulacion.assettrack.dto.ReportePreventivoDTO;
import com.proyecto_titulacion.assettrack.mapper.ReporteCorrectivoMapper;
import com.proyecto_titulacion.assettrack.mapper.ReportePreventivoMapper;
import com.proyecto_titulacion.assettrack.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GestorReporteServiceImpl implements GestorReporteService {
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @Autowired
    private ReporteService reporteService;
    @Autowired
    private ReportePreventivoService preventivoService;
    @Autowired
    private ReporteCorrectivoService correctivoService;

    private Reporte createReporte(OrdenTrabajo ordenTrabajo) {
        Reporte reporte = new Reporte();
        reporte.setEmpresaId(ordenTrabajo.getEmpresaId());
        reporte.setSucursalId(ordenTrabajo.getSucursalId());
        reporte.setActivoId(ordenTrabajo.getActivoId());
        reporte.setTipoMantenimiento(ordenTrabajo.getTipoMantenimiento());
        reporte.setFecha(new Date());

        return reporte;
    }

    @Override
    public Boolean createReportePreventivo(Long ordenTrabajoId, ReportePreventivo reportePreventivo) {
        OrdenTrabajo ordenTrabajo = this.ordenTrabajoService.getOrdenesTrabajoById(ordenTrabajoId);

        Reporte reporte = this.createReporte(ordenTrabajo);

        reporte.setPreventivo(reportePreventivo);
        reportePreventivo.setReporte(reporte);

        ReportePreventivoMapper preventivoMapper = new ReportePreventivoMapper();

        Reporte newReporte = this.reporteService.createReporte(reporte);
        ReportePreventivoDTO newPreventivo = this.preventivoService.createReporte(preventivoMapper.toDTO(reportePreventivo));

        this.ordenTrabajoService.deleteOrdenTrabajo(ordenTrabajoId);

        return true;
    }

    @Override
    public Boolean createReporteCorrectivo(Long ordenTrabajoId, ReporteCorrectivo reporteCorrectivo) {
        OrdenTrabajo ordenTrabajo = this.ordenTrabajoService.getOrdenesTrabajoById(ordenTrabajoId);

        Reporte reporte = this.createReporte(ordenTrabajo);

        reporte.setCorrectivo(reporteCorrectivo);
        reporteCorrectivo.setReporte(reporte);

        ReporteCorrectivoMapper correctivoMapper = new ReporteCorrectivoMapper();

        Reporte newReporte = this.reporteService.createReporte(reporte);
        ReporteCorrectivoDTO newCorrectivo = this.correctivoService.createReporte(correctivoMapper.toDTO(reporteCorrectivo));

        this.ordenTrabajoService.deleteOrdenTrabajo(ordenTrabajoId);

        return true;
    }

    @Override
    public ReportePreventivoDTO getReportePreventivo(Reporte reporte) {
        if (!reporte.getTipoMantenimiento().equals(Mantenimiento.PREVENTIVO)) {
            throw new IllegalArgumentException("El tipo de reporte no corresponde a un Reporte Preventivo");
        }
        return this.preventivoService.getReporte(reporte);
    }

    @Override
    public ReporteCorrectivoDTO getReporteCorrectivo(Reporte reporte) {
        if (!reporte.getTipoMantenimiento().equals(Mantenimiento.CORRECTIVO)) {
            throw new IllegalArgumentException("El tipo de reporte no corresponde a un Reporte Correctivo");
        }
        return this.correctivoService.getReporte(reporte);
    }
}

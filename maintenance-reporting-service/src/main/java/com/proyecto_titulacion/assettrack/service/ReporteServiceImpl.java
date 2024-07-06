package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.Reporte;
import com.proyecto_titulacion.assettrack.repository.ReporteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReporteServiceImpl implements ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;
    @Override
    public Page<Reporte> getAllReporte(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fecha"));
        Page<Reporte> reportePage = this.reporteRepository.findAll(pageable);
        return reportePage;
    }

    @Override
    public Reporte getReporteById(Long id) {
        Reporte reporte = this.reporteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reporte no encontrado con ID: " + id));
        return reporte;
    }

    @Override
    public Reporte createReporte(Reporte create) {
        Reporte newReporte = this.reporteRepository.save(create);
        return newReporte;
    }

    @Override
    public Reporte updateReporte(Long id, Reporte update) {
        Reporte reporte = this.reporteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reporte no encontrado con ID: " + id));
        reporte.setEmpresaId(update.getEmpresaId());
        reporte.setSucursalId(update.getSucursalId());
        reporte.setActivoId(update.getActivoId());
        reporte.setTipoMantenimiento(update.getTipoMantenimiento());
        reporte.setFecha(update.getFecha());

        Reporte updateReporte = this.reporteRepository.save(reporte);
        return updateReporte;
    }

    @Override
    public Reporte deleteReporte(Long id) {
        Reporte reporte = this.reporteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reporte no encontrado con ID: " + id));;
        this.reporteRepository.delete(reporte);
        return reporte;
    }
}

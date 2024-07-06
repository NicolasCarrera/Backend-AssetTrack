package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.exception.ResourceNotFoundException;
import com.proyecto_titulacion.assettrack.model.OrdenTrabajo;
import com.proyecto_titulacion.assettrack.repository.OrdenTrabajoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrdenTrabajoServiceImpl implements OrdenTrabajoService{
    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;
    @Override
    public Page<OrdenTrabajo> getAllOrdenesTrabajo(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "fechaProgramada"));
        Page<OrdenTrabajo> ordenTrabajoPage = this.ordenTrabajoRepository.findAll(pageable);
        return ordenTrabajoPage;
    }

    @Override
    public OrdenTrabajo getOrdenesTrabajoById(Long id) {
        OrdenTrabajo ordenTrabajo = this.ordenTrabajoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Orden de Trabajo no encontrado con ID: " + id));
        return ordenTrabajo;
    }

    @Override
    public OrdenTrabajo createOrdenTrabajo(OrdenTrabajo create) {
        OrdenTrabajo newOrdenTrabajo = this.ordenTrabajoRepository.save(create);
        return newOrdenTrabajo;
    }

    @Override
    public OrdenTrabajo updateOrdenTrabajo(Long id, OrdenTrabajo ordenTrabajo) {
        OrdenTrabajo ordenTrabajoDB = this.ordenTrabajoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Orden de Trabajo no encontrado con ID: " + id));
        ordenTrabajoDB.setEmpresaId(ordenTrabajo.getEmpresaId());
        ordenTrabajoDB.setSucursalId(ordenTrabajo.getSucursalId());
        ordenTrabajoDB.setActivoId(ordenTrabajo.getActivoId());
        ordenTrabajoDB.setTipoMantenimiento(ordenTrabajo.getTipoMantenimiento());
        ordenTrabajoDB.setFechaProgramada(ordenTrabajo.getFechaProgramada());
        ordenTrabajoDB.setEstadoOrden(ordenTrabajo.getEstadoOrden());
        ordenTrabajoDB.setTareas(ordenTrabajo.getTareas());

        OrdenTrabajo ordenTrabajoUpdate = this.ordenTrabajoRepository.save(ordenTrabajoDB);
        return ordenTrabajoUpdate;
    }

    @Override
    public OrdenTrabajo deleteOrdenTrabajo(Long id) {
        OrdenTrabajo ordenTrabajo = this.ordenTrabajoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Orden de Trabajo no encontrado con ID: " + id));
        this.ordenTrabajoRepository.delete(ordenTrabajo);
        return ordenTrabajo;
    }
}

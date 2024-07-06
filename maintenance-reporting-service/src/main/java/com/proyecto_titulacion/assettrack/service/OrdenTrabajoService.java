package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.OrdenTrabajo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdenTrabajoService {
    Page<OrdenTrabajo> getAllOrdenesTrabajo(int page, int size);
    OrdenTrabajo getOrdenesTrabajoById(Long id);
    OrdenTrabajo createOrdenTrabajo(OrdenTrabajo create);
    OrdenTrabajo updateOrdenTrabajo(Long id, OrdenTrabajo ordenTrabajo);
    OrdenTrabajo deleteOrdenTrabajo(Long id);

}

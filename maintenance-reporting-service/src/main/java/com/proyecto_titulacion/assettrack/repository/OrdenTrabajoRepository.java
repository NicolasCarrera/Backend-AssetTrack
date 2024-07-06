package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.OrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo,Long> {
}

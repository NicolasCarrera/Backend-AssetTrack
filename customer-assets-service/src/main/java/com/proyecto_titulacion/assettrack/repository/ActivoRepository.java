package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.Activo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoRepository extends JpaRepository<Activo, Long> {
}

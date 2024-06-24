package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}

package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.ReportePreventivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportePreventivoRepository extends JpaRepository<ReportePreventivo, Long> {
}

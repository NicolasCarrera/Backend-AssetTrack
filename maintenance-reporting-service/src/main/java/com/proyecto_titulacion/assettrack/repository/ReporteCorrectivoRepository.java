package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.ReporteCorrectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteCorrectivoRepository extends JpaRepository<ReporteCorrectivo, Long> {
}

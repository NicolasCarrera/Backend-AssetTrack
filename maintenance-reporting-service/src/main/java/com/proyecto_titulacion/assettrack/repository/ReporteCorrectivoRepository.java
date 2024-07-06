package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.Reporte;
import com.proyecto_titulacion.assettrack.model.ReporteCorrectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteCorrectivoRepository extends JpaRepository<ReporteCorrectivo, Long> {
    @Query("SELECT rc FROM ReporteCorrectivo rc JOIN rc.reporte r WHERE r = :reporte")
    ReporteCorrectivo getReporte(@Param("reporte") Reporte reporte);
}

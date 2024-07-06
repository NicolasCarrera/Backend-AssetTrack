package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.Reporte;
import com.proyecto_titulacion.assettrack.model.ReporteCorrectivo;
import com.proyecto_titulacion.assettrack.model.ReportePreventivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportePreventivoRepository extends JpaRepository<ReportePreventivo, Long> {
    @Query("SELECT rp FROM ReportePreventivo rp JOIN rp.reporte r WHERE r = :reporte")
    ReportePreventivo getReporte(@Param("reporte") Reporte reporte);
}

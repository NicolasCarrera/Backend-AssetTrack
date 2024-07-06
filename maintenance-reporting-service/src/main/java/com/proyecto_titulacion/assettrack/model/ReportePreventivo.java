package com.proyecto_titulacion.assettrack.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "reporte_preventivo")
public class ReportePreventivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reporte_id", updatable = false, nullable = false)
    private Reporte reporte;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @Column(name = "descripcion_trabajo", nullable = false)
    private String descripcionTrabajo;

    @Column(name = "partes_inspeccionadas")
    private String partesInspeccionadas;

    @Column(name = "partes_reemplazadas")
    private String partesReemplazadas;

    @Column(name = "resultados_inspeccion")
    private String resultadosInspeccion;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "tecnico_encargado", nullable = false)
    private String tecnicoEncargado;

    @Column(name = "recomendaciones_futuras")
    private String recomendacionesFuturas;

}

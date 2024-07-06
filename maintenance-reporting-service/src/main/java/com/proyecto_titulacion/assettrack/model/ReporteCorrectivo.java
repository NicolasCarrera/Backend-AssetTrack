package com.proyecto_titulacion.assettrack.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "reporte_correctivo")
public class ReporteCorrectivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reporte_id", updatable = false, nullable = false)
    private Reporte reporte;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_incidente", nullable = false)
    private Date fechaIncidente;

    @Column(name = "descripcion_problema", nullable = false)
    private String descripcionProblema;

    @Column(name = "analisis_problema", nullable = false)
    private String analisisProblema;

    @Column(name = "causa_raiz")
    private String causaRaiz;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio_reparacion", nullable = false)
    private Date fechaInicioReparacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fin_reparacion", nullable = false)
    private Date fechaFinReparacion;

    @Column(name = "descripcion_trabajo", nullable = false)
    private String descripcionTrabajo;

    @Column(name = "partes_danadas")
    private String partesDanadas;

    @Column(name = "partes_reemplazadas")
    private String partesReemplazadas;

    @Column(name = "estado_despues_reparacion")
    private String estadoDespuesReparacion;

    @Column(name = "tiempo_inactividad")
    private String tiempoInactividad;

    @Column(name = "impacto_operacion")
    private String impactoOperacion;

    @Column(name = "recomendaciones_futuras")
    private String recomendacionesFuturas;

    @Column(name = "tecnico_encargado", nullable = false)
    private String tecnicoEncargado;
}

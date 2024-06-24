package com.proyecto_titulacion.assettrack.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "reporte_preventivo")
public class ReportePreventivo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Column(name = "activo")
    private List<String> activo;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getActivo() {
        return activo;
    }

    public void setActivo(List<String> activo) {
        this.activo = activo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo(String descripcionTrabajo) {
        this.descripcionTrabajo = descripcionTrabajo;
    }

    public String getPartesInspeccionadas() {
        return partesInspeccionadas;
    }

    public void setPartesInspeccionadas(String partesInspeccionadas) {
        this.partesInspeccionadas = partesInspeccionadas;
    }

    public String getPartesReemplazadas() {
        return partesReemplazadas;
    }

    public void setPartesReemplazadas(String partesReemplazadas) {
        this.partesReemplazadas = partesReemplazadas;
    }

    public String getResultadosInspeccion() {
        return resultadosInspeccion;
    }

    public void setResultadosInspeccion(String resultadosInspeccion) {
        this.resultadosInspeccion = resultadosInspeccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTecnicoEncargado() {
        return tecnicoEncargado;
    }

    public void setTecnicoEncargado(String tecnicoEncargado) {
        this.tecnicoEncargado = tecnicoEncargado;
    }

    public String getRecomendacionesFuturas() {
        return recomendacionesFuturas;
    }

    public void setRecomendacionesFuturas(String recomendacionesFuturas) {
        this.recomendacionesFuturas = recomendacionesFuturas;
    }
}

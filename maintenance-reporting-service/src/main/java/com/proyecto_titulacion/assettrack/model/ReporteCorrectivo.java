package com.proyecto_titulacion.assettrack.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "reporte_correctivo")
public class ReporteCorrectivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Column(name = "activo")
    private List<String> activo;

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

    public Date getFechaIncidente() {
        return fechaIncidente;
    }

    public void setFechaIncidente(Date fechaIncidente) {
        this.fechaIncidente = fechaIncidente;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getAnalisisProblema() {
        return analisisProblema;
    }

    public void setAnalisisProblema(String analisisProblema) {
        this.analisisProblema = analisisProblema;
    }

    public String getCausaRaiz() {
        return causaRaiz;
    }

    public void setCausaRaiz(String causaRaiz) {
        this.causaRaiz = causaRaiz;
    }

    public Date getFechaInicioReparacion() {
        return fechaInicioReparacion;
    }

    public void setFechaInicioReparacion(Date fechaInicioReparacion) {
        this.fechaInicioReparacion = fechaInicioReparacion;
    }

    public Date getFechaFinReparacion() {
        return fechaFinReparacion;
    }

    public void setFechaFinReparacion(Date fechaFinReparacion) {
        this.fechaFinReparacion = fechaFinReparacion;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo(String descripcionTrabajo) {
        this.descripcionTrabajo = descripcionTrabajo;
    }

    public String getPartesDanadas() {
        return partesDanadas;
    }

    public void setPartesDanadas(String partesDanadas) {
        this.partesDanadas = partesDanadas;
    }

    public String getPartesReemplazadas() {
        return partesReemplazadas;
    }

    public void setPartesReemplazadas(String partesReemplazadas) {
        this.partesReemplazadas = partesReemplazadas;
    }

    public String getEstadoDespuesReparacion() {
        return estadoDespuesReparacion;
    }

    public void setEstadoDespuesReparacion(String estadoDespuesReparacion) {
        this.estadoDespuesReparacion = estadoDespuesReparacion;
    }

    public String getTiempoInactividad() {
        return tiempoInactividad;
    }

    public void setTiempoInactividad(String tiempoInactividad) {
        this.tiempoInactividad = tiempoInactividad;
    }

    public String getImpactoOperacion() {
        return impactoOperacion;
    }

    public void setImpactoOperacion(String impactoOperacion) {
        this.impactoOperacion = impactoOperacion;
    }

    public String getRecomendacionesFuturas() {
        return recomendacionesFuturas;
    }

    public void setRecomendacionesFuturas(String recomendacionesFuturas) {
        this.recomendacionesFuturas = recomendacionesFuturas;
    }

    public String getTecnicoEncargado() {
        return tecnicoEncargado;
    }

    public void setTecnicoEncargado(String tecnicoEncargado) {
        this.tecnicoEncargado = tecnicoEncargado;
    }
}

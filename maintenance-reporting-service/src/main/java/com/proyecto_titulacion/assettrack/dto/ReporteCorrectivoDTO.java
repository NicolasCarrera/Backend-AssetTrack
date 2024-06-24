package com.proyecto_titulacion.assettrack.dto;

import java.util.Date;
import java.util.List;

public class ReporteCorrectivoDTO {


    private Long id;
    private List<String> activo;
    private Date fechaIncidente;
    private String descripcionProblema;
    private String analisisProblema;
    private String causaRaiz;
    private Date fechaInicioReparacion;
    private Date fechaFinReparacion;
    private String descripcionTrabajo;
    private String partesDanadas;
    private String partesReemplazadas;
    private String estadoDespuesReparacion;
    private String tiempoInactividad;
    private String impactoOperacion;
    private String recomendacionesFuturas;
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

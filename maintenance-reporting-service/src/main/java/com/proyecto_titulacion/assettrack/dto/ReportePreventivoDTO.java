package com.proyecto_titulacion.assettrack.dto;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReportePreventivoDTO {

    private Long id;
    private List<String> activo;
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcionTrabajo;
    private String partesInspeccionadas;
    private String partesReemplazadas;
    private String resultadosInspeccion;
    private String observaciones;
    private String tecnicoEncargado;
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

package com.proyecto_titulacion.assettrack.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReporteCorrectivoDTO {


    private Long id;
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

}

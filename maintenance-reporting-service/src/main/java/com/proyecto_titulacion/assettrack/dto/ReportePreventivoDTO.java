package com.proyecto_titulacion.assettrack.dto;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReportePreventivoDTO {

    private Long id;
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcionTrabajo;
    private String partesInspeccionadas;
    private String partesReemplazadas;
    private String resultadosInspeccion;
    private String observaciones;
    private String tecnicoEncargado;
    private String recomendacionesFuturas;

}

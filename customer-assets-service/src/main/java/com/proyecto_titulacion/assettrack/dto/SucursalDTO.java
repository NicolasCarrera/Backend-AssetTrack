package com.proyecto_titulacion.assettrack.dto;

import lombok.Data;

@Data
public class SucursalDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private Long numeroEmpleados;
    private Long numeroActivos;
    private Long empresaId;

}

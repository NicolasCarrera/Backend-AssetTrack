package com.proyecto_titulacion.assettrack.dto;


import lombok.Data;

@Data
public class EmpresaDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String sectorIndustrial;
    private Long numeroEmpleados;
    private Long numeroActivos;
    // List<SucursalDTO> sucursales; // Opcional, dependiendo de tus necesidades


}

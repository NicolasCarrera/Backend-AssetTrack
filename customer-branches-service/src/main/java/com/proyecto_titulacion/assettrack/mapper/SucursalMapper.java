package com.proyecto_titulacion.assettrack.mapper;


import com.proyecto_titulacion.assettrack.dto.SucursalDTO;
import com.proyecto_titulacion.assettrack.model.Sucursal;
import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {

    public SucursalDTO toDTO(Sucursal sucursal) {
        SucursalDTO dto = new SucursalDTO();
        dto.setId(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());
        dto.setTelefono(sucursal.getTelefono());
        dto.setEmail(sucursal.getEmail());
        dto.setNumeroEmpleados(sucursal.getNumeroEmpleados());
        dto.setNumeroActivos(sucursal.getNumeroActivos());
        dto.setEmpresaId(sucursal.getEmpresa().getId()); // Puedes usar el ID de empresa aquí
        return dto;
    }

    public Sucursal toEntity(SucursalDTO dto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(dto.getId());
        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setTelefono(dto.getTelefono());
        sucursal.setEmail(dto.getEmail());
        sucursal.setNumeroEmpleados(dto.getNumeroEmpleados());
        sucursal.setNumeroActivos(dto.getNumeroActivos());
        // Debes manejar la relación con la empresa aquí si es necesario
        return sucursal;
    }
}

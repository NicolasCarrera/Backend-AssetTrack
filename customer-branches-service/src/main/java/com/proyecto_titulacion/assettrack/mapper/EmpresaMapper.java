package com.proyecto_titulacion.assettrack.mapper;


import com.proyecto_titulacion.assettrack.dto.EmpresaDTO;
import com.proyecto_titulacion.assettrack.model.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {

    public EmpresaDTO toDTO(Empresa empresa) {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(empresa.getId());
        dto.setNombre(empresa.getNombre());
        dto.setDireccion(empresa.getDireccion());
        dto.setTelefono(empresa.getTelefono());
        dto.setEmail(empresa.getEmail());
        dto.setSectorIndustrial(empresa.getSectorIndustrial());
        dto.setNumeroEmpleados(empresa.getNumeroEmpleados());
        dto.setNumeroActivos(empresa.getNumeroActivos());
        // Puedes añadir la conversión de sucursales aquí si es necesario
        return dto;
    }

    public Empresa toEntity(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setId(dto.getId());
        empresa.setNombre(dto.getNombre());
        empresa.setDireccion(dto.getDireccion());
        empresa.setTelefono(dto.getTelefono());
        empresa.setEmail(dto.getEmail());
        empresa.setSectorIndustrial(dto.getSectorIndustrial());
        empresa.setNumeroEmpleados(dto.getNumeroEmpleados());
        empresa.setNumeroActivos(dto.getNumeroActivos());
        // Puedes manejar la conversión de sucursales aquí si es necesario
        return empresa;
    }

}

package com.proyecto_titulacion.assettrack.mapper;

import com.proyecto_titulacion.assettrack.dto.ActivoDTO;
import com.proyecto_titulacion.assettrack.model.Activo;

import org.springframework.stereotype.Component;


@Component
public class ActivoMapper {

    public ActivoDTO toDTO(Activo activo) {
        if (activo == null) {
            return null;
        }
        ActivoDTO dto = new ActivoDTO();
        dto.setId(activo.getId());
        dto.setNombre(activo.getNombre());
        dto.setDescripcion(activo.getDescripcion());
        dto.setCategoria(activo.getCategoria());
        dto.setMarca(activo.getMarca());
        dto.setTipo(activo.getTipo());
        dto.setEmpresa(activo.getEmpresa());
        dto.setSucursal(activo.getSucursal());
        dto.setArea(activo.getArea());
        dto.setFechaAdquisicion(activo.getFechaAdquisicion());
        dto.setSerial(activo.getSerial());
        dto.setFechaUltimoMantenimiento(activo.getFechaUltimoMantenimiento());
        dto.setFechaProximoMantenimiento(activo.getFechaProximoMantenimiento());
        dto.setEmpresaMantenimiento(activo.getEmpresaMantenimiento());
        dto.setProveedor(activo.getProveedor());
        return dto;
    }

    public Activo toEntity(ActivoDTO dto) {
        if (dto == null) {
            return null;
        }
        Activo activo = new Activo();
        activo.setId(dto.getId());
        activo.setNombre(dto.getNombre());
        activo.setDescripcion(dto.getDescripcion());
        activo.setCategoria(dto.getCategoria());
        activo.setMarca(dto.getMarca());
        activo.setTipo(dto.getTipo());
        activo.setEmpresa(dto.getEmpresa());
        activo.setSucursal(dto.getSucursal());
        activo.setArea(dto.getArea());
        activo.setFechaAdquisicion(dto.getFechaAdquisicion());
        activo.setSerial(dto.getSerial());
        activo.setFechaUltimoMantenimiento(dto.getFechaUltimoMantenimiento());
        activo.setFechaProximoMantenimiento(dto.getFechaProximoMantenimiento());
        activo.setEmpresaMantenimiento(dto.getEmpresaMantenimiento());
        activo.setProveedor(dto.getProveedor());
        return activo;
    }
}

package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.SucursalDTO;

import java.util.List;

public interface SucursalService {

    public List<SucursalDTO> getAllSucursales();

    public SucursalDTO getSucursalById(Long id);

    public SucursalDTO createSucursal(SucursalDTO sucursalDTO);

    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDTO);

    public void deleteSucursal(Long id);
}

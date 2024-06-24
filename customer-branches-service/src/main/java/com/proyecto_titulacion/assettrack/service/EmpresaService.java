package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.EmpresaDTO;

import java.util.List;

public interface EmpresaService {

    public List<EmpresaDTO> getAllEmpresas();

    public EmpresaDTO getEmpresaById(Long id);

    public EmpresaDTO createEmpresa(EmpresaDTO empresaDTO);

    public EmpresaDTO updateEmpresa(Long id, EmpresaDTO empresaDTO);

    public void deleteEmpresa(Long id);

}

package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.ReporteCorrectivoDTO;

import java.util.List;

public interface ReporteCorrectivoService {


    public List<ReporteCorrectivoDTO> getAllReportes();

    public ReporteCorrectivoDTO getReporteById(Long id);

    public ReporteCorrectivoDTO createReporte(ReporteCorrectivoDTO dto);

    public ReporteCorrectivoDTO updateReporte(Long id, ReporteCorrectivoDTO dto);

    public void deleteReporte(Long id);

}

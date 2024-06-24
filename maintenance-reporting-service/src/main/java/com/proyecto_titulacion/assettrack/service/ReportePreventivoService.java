package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.ReportePreventivoDTO;

import java.util.List;

public interface ReportePreventivoService {

    public List<ReportePreventivoDTO> getAllReportes();

    public ReportePreventivoDTO getReporteById(Long id);

    public ReportePreventivoDTO createReporte(ReportePreventivoDTO dto);

    public ReportePreventivoDTO updateReporte(Long id, ReportePreventivoDTO dto);

    public void deleteReporte(Long id);



}

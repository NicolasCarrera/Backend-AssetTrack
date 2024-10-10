package com.proyecto_titulacion.assettrack.service;

import com.lowagie.text.DocumentException;
import com.proyecto_titulacion.assettrack.client.asset.model.AssetDTO;
import com.proyecto_titulacion.assettrack.client.asset.service.AssetClient;
import com.proyecto_titulacion.assettrack.client.customer.model.BranchDTO;
import com.proyecto_titulacion.assettrack.client.customer.model.CompanyDTO;
import com.proyecto_titulacion.assettrack.client.customer.service.CompanyBranchClient;
import com.proyecto_titulacion.assettrack.client.user.model.UserDTO;
import com.proyecto_titulacion.assettrack.client.user.service.UserClient;
import com.proyecto_titulacion.assettrack.model.dto.MaintenanceReportDTO;
import com.proyecto_titulacion.assettrack.model.entity.MaintenanceReport;
import com.proyecto_titulacion.assettrack.util.feign.FeignUtil;
import com.proyecto_titulacion.assettrack.util.openpdf.CorrectiveMaintenanceReport;
import com.proyecto_titulacion.assettrack.util.openpdf.PreventiveMaintenanceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentReportServiceImpl implements DocumentReportService {
    @Autowired
    private MaintenanceReportService maintenanceReportService;
    @Autowired
    private AssetClient assetClient;
    @Autowired
    private CompanyBranchClient companyBranchClient;
    @Autowired
    private UserClient userClient;

    @Override
    public byte[] generateReport(Long id) throws DocumentException {
        MaintenanceReport maintenanceReport = this.maintenanceReportService.getMaintenanceReportById(id);

        UserDTO user = FeignUtil.safeFeignCall(() ->
                this.userClient.getUserById(maintenanceReport.getUserId())
        );
        AssetDTO asset = FeignUtil.safeFeignCall(() ->
                this.assetClient.getAssetById(maintenanceReport.getAssetId())
        );
        BranchDTO branch = FeignUtil.safeFeignCall(() ->
                this.companyBranchClient.getBranchById(maintenanceReport.getBranchId())
        );
        CompanyDTO company = FeignUtil.safeFeignCall(() ->
                this.companyBranchClient.getCompanyById(maintenanceReport.getCompanyId())
        );

        MaintenanceReportDTO maintenanceReportDTO = MaintenanceReportDTO.maintenanceReportDTO(maintenanceReport, user, asset, branch, company);

        return switch (maintenanceReportDTO.type()) {
            case PREVENTIVE -> PreventiveMaintenanceReport.generatePdf(maintenanceReportDTO);
            case CORRECTIVE -> CorrectiveMaintenanceReport.generatePdf(maintenanceReportDTO);
        };
    }
}

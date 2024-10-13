package com.proyecto_titulacion.assettrack.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.proyecto_titulacion.assettrack.client.asset.model.AssetDTO;
import com.proyecto_titulacion.assettrack.client.customer.model.BranchDTO;
import com.proyecto_titulacion.assettrack.client.customer.model.CompanyDTO;
import com.proyecto_titulacion.assettrack.client.user.model.UserDTO;
import com.proyecto_titulacion.assettrack.model.entity.MaintenanceReport;
import com.proyecto_titulacion.assettrack.model.type.MaintenanceType;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MaintenanceReportDTO(
        Long id,
        MaintenanceType type,
        LocalDate date,
        List<String> observations,
        List<String> recommendations,
        UserDTO user,
        AssetDTO asset,
        BranchDTO branch,
        CompanyDTO company,
        List<MaintenanceActivityDTO> maintenanceActivities,
        PreventiveMaintenanceDTO preventiveMaintenance,
        CorrectiveMaintenanceDTO correctiveMaintenance
) {
    public static MaintenanceReportDTO maintenanceReportDTO(MaintenanceReport maintenanceReport, UserDTO user, AssetDTO asset, BranchDTO branch, CompanyDTO company) {
        return Objects.isNull(maintenanceReport)
                ? null
                : new MaintenanceReportDTO(
                maintenanceReport.getId(),
                maintenanceReport.getType(),
                maintenanceReport.getDate(),
                maintenanceReport.getObservations(),
                maintenanceReport.getRecommendations(),
                user,
                asset,
                branch,
                company,
                maintenanceReport.getMaintenanceActivities().stream().map(MaintenanceActivityDTO::toMaintenanceActivityDTO).toList(),
                PreventiveMaintenanceDTO.toPreventiveMaintenanceDTO(maintenanceReport.getPreventiveMaintenance()),
                CorrectiveMaintenanceDTO.toCorrectiveMaintenanceDTO(maintenanceReport.getCorrectiveMaintenance())
        );
    }

    public MaintenanceReport maintenanceReport() {
        return MaintenanceReport.builder()
                .id(this.id)
                .type(this.type)
                .date(this.date)
                .observations(this.observations)
                .recommendations(this.recommendations)
                .userId(this.user != null ? this.user.id() : null)
                .assetId(this.asset != null ? this.asset.id() : null)
                .branchId(this.branch != null ? this.asset().id() : null)
                .companyId(this.company != null ? this.company.id() : null)
                .maintenanceActivities(this.maintenanceActivities.stream().map(MaintenanceActivityDTO::toMaintenanceActivity).toList())
                .preventiveMaintenance(this.preventiveMaintenance.toPreventiveMaintenance())
                .correctiveMaintenance(this.correctiveMaintenance.toCorrectiveMaintenance())
                .build();
    }
}

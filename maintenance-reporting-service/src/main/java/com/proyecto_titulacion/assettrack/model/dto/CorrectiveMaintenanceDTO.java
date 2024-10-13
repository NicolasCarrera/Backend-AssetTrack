package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.entity.CorrectiveMaintenance;

import java.util.List;
import java.util.Objects;

public record CorrectiveMaintenanceDTO(
        Long id,
        String issueDescription,
        String failureCause,
        String postMaintenanceStatus,
        List<DiagnosticActionDTO> diagnosticActions
) {
    public static CorrectiveMaintenanceDTO toCorrectiveMaintenanceDTO(CorrectiveMaintenance correctiveMaintenance) {
        return Objects.isNull(correctiveMaintenance)
                ? null
                : new CorrectiveMaintenanceDTO(
                correctiveMaintenance.getId(),
                correctiveMaintenance.getIssueDescription(),
                correctiveMaintenance.getFailureCause(),
                correctiveMaintenance.getPostMaintenanceStatus(),
                correctiveMaintenance.getDiagnosticActions().stream().map(DiagnosticActionDTO::toDiagnosticActionDTO).toList()
        );
    }

    public CorrectiveMaintenance toCorrectiveMaintenance() {
        return CorrectiveMaintenance.builder()
                .id(this.id)
                .issueDescription(this.issueDescription)
                .failureCause(this.failureCause)
                .postMaintenanceStatus(this.postMaintenanceStatus)
                .diagnosticActions(this.diagnosticActions.stream().map(DiagnosticActionDTO::toDiagnosticAction).toList())
                .build();
    }
}

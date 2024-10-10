package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.entity.DiagnosticAction;

import java.util.Set;

public record DiagnosticActionDTO(
        Long id,
        String action,
        Set<String> descriptions
) {
    public static DiagnosticActionDTO toDiagnosticActionDTO(DiagnosticAction diagnosticAction){
        return new DiagnosticActionDTO(
                diagnosticAction.getId(),
                diagnosticAction.getAction(),
                diagnosticAction.getDescriptions()
        );
    }
    public DiagnosticAction toDiagnosticAction() {
        return DiagnosticAction.builder()
                .id(this.id)
                .action(this.action)
                .descriptions(this.descriptions)
                .build();
    }
}

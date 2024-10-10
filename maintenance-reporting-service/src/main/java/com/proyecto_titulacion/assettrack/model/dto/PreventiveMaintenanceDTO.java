package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.entity.PreventiveMaintenance;

public record PreventiveMaintenanceDTO(
        Long id,
        String frequency
) {
    public static PreventiveMaintenanceDTO toPreventiveMaintenanceDTO(PreventiveMaintenance preventiveMaintenance){
        return new PreventiveMaintenanceDTO(
                preventiveMaintenance.getId(),
                preventiveMaintenance.getFrequency()
        );
    }
    public PreventiveMaintenance toPreventiveMaintenance() {
        return PreventiveMaintenance.builder()
                .id(this.id)
                .frequency(this.frequency)
                .build();
    }
}

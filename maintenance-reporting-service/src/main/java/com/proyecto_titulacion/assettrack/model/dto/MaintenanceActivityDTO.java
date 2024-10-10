package com.proyecto_titulacion.assettrack.model.dto;

import com.proyecto_titulacion.assettrack.model.entity.MaintenanceActivity;

import java.util.Set;

public record MaintenanceActivityDTO(
        Long id,
        String name,
        Set<String> tasks
) {
    public static MaintenanceActivityDTO toMaintenanceActivityDTO(MaintenanceActivity maintenanceActivity){
        return new MaintenanceActivityDTO(
                maintenanceActivity.getId(),
                maintenanceActivity.getName(),
                maintenanceActivity.getTasks()
        );
    }

    public MaintenanceActivity toMaintenanceActivity() {
        return MaintenanceActivity.builder()
                .id(this.id)
                .name(this.name)
                .tasks(this.tasks)
                .build();
    }
}

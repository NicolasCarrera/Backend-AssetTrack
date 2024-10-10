package com.proyecto_titulacion.assettrack.model.dto;

import java.time.LocalDate;

public record MaintenanceDTO(
        LocalDate last,
        LocalDate next
) {
}

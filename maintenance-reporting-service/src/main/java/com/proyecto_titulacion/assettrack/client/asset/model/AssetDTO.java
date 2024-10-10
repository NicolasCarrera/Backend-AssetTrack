package com.proyecto_titulacion.assettrack.client.asset.model;

import java.time.LocalDate;

public record AssetDTO(
        Long id,
        String name,
        String description,
        String brand,
        String model,
        String serial,
        LocalDate acquisition,
        String location
) {
}

package com.proyecto_titulacion.assettrack.client.asset.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

package com.proyecto_titulacion.assettrack.client.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BranchDTO(
        Long id,
        String name,
        String location,
        String email,
        String phone
) {
}

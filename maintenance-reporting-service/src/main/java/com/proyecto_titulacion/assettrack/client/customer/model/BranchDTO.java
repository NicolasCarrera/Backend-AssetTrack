package com.proyecto_titulacion.assettrack.client.customer.model;

public record BranchDTO(
        Long id,
        String name,
        String location,
        String email,
        String phone
) {
}

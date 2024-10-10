package com.proyecto_titulacion.assettrack.client.user.model;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}

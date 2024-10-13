package com.proyecto_titulacion.assettrack.client.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}

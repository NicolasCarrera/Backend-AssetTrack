package com.proyecto_titulacion.assettrack.client.customer.model;

import com.proyecto_titulacion.assettrack.client.user.model.UserDTO;

public record CompanyDTO(
        Long id,
        String name,
        UserDTO user
) {
}

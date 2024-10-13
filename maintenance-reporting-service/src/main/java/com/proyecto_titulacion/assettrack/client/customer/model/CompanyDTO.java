package com.proyecto_titulacion.assettrack.client.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.proyecto_titulacion.assettrack.client.user.model.UserDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CompanyDTO(
        Long id,
        String name,
        UserDTO user
) {
}

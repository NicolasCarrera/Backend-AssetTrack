package com.proyecto_titulacion.assettrack.dto;

import java.util.List;

public record CreateRole(String roleName, List<String> permissions) {
}

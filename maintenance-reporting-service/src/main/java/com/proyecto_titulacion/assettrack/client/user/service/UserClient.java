package com.proyecto_titulacion.assettrack.client.user.service;

import com.proyecto_titulacion.assettrack.client.user.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-role-management-service")
public interface UserClient {
    @GetMapping("/api/v1/users/{userId}")
    UserDTO getUserById(@PathVariable("userId") Long userId);
}

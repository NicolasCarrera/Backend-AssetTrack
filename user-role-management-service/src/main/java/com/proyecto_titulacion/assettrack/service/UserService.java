package com.proyecto_titulacion.assettrack.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto_titulacion.assettrack.model.dto.UserDTO;
import com.proyecto_titulacion.assettrack.model.dto.CreateUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    String loginUser(String identification, String password) throws JsonProcessingException;

    Page<UserDTO> getAllUsers(int page, int size);

    List<UserDTO> getUsersByRole(String role);

    Page<UserDTO> getUsersByFilter(String filter, int page, int size);

    UserDTO createUser(CreateUser createUser);

    UserDTO getUserById(Long id);
    UserDTO updateUser(Long id, CreateUser updateUser);

    void deleteUsers(Long id);
}

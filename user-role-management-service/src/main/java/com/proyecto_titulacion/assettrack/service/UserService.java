package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.AuthenticationLogin;
import com.proyecto_titulacion.assettrack.dto.AuthenticationResponse;
import com.proyecto_titulacion.assettrack.dto.CreateUser;
import com.proyecto_titulacion.assettrack.dto.UserRecord;
import com.proyecto_titulacion.assettrack.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface UserService {
    Page<UserRecord> getAllUsers(Pageable pageable);
    UserRecord getUserById(Long id);
    UserRecord createUser(CreateUser createUser);
    UserRecord updateUser(Long id, CreateUser userDetails);
    UserRecord disableUser(Long id);
    AuthenticationResponse loginUser(AuthenticationLogin userCredentials);
    Authentication authenticateUser(String username, String password);
}

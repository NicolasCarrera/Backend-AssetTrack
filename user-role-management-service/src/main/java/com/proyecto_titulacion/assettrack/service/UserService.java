package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.AuthenticationLogin;
import com.proyecto_titulacion.assettrack.dto.AuthenticationResponse;
import com.proyecto_titulacion.assettrack.dto.CreateUser;
import com.proyecto_titulacion.assettrack.dto.UserRecord;
import com.proyecto_titulacion.assettrack.model.UserEntity;
import org.springframework.security.core.Authentication;

public interface UserService {
    UserRecord getUserById(Long id);
    UserRecord createUser(CreateUser createUser);
    UserRecord updateUser(Long id, UserRecord userDetails);
    UserRecord disableUser(Long id);
    AuthenticationResponse loginUser(AuthenticationLogin userCredentials);
    Authentication authenticateUser(String username, String password);
}

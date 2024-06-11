package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.dto.AuthenticationLogin;
import com.proyecto_titulacion.assettrack.dto.AuthenticationResponse;
import com.proyecto_titulacion.assettrack.dto.CreateUser;
import com.proyecto_titulacion.assettrack.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody CreateUser newUser){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(newUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el usuario");
        }
    }

    @PostMapping("/log-in")
    public ResponseEntity<?> login(@RequestBody AuthenticationLogin userCredentials){
        try {
            AuthenticationResponse authenticationResponse = this.userService.loginUser(userCredentials);
            if (authenticationResponse == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
            }
            return ResponseEntity.ok(authenticationResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al autenticar el usuario");
        }
    }
}

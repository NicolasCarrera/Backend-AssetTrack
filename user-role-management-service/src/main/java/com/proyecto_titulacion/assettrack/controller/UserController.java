package com.proyecto_titulacion.assettrack.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto_titulacion.assettrack.model.dto.AuthenticationLogin;
import com.proyecto_titulacion.assettrack.model.dto.UserDTO;
import com.proyecto_titulacion.assettrack.model.dto.CreateUser;
import com.proyecto_titulacion.assettrack.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthenticationLogin userCredentials) throws JsonProcessingException {
        String user = this.userService.loginUser(userCredentials.identification(), userCredentials.password());
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<UserDTO> users = this.userService.getAllUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@PathVariable("role") String role) {
        List<UserDTO> users = this.userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<UserDTO>> getUsersByFilter(
            @RequestParam("filter") String filter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<UserDTO> users = this.userService.getUsersByFilter(filter, page, size);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUser createUser) {
        UserDTO user = this.userService.createUser(createUser);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        try {
            UserDTO user = this.userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody CreateUser updateUser) {
        UserDTO user = this.userService.updateUser(id, updateUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable("id") Long id) {
        try {
            this.userService.deleteUsers(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.dto.UserRecord;
import com.proyecto_titulacion.assettrack.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserRecord> getUser(@PathVariable Long id) {
        try {
            UserRecord user = this.userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PatchMapping("/{id}/disable")
    public ResponseEntity<UserRecord> disableUser(@PathVariable Long id) {
        try {
            UserRecord disabledUser = this.userService.disableUser(id);
            return ResponseEntity.ok(disabledUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRecord> updateUser(@PathVariable Long id, @RequestBody UserRecord userDetails) {
        try {
            UserRecord updatedUser = this.userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

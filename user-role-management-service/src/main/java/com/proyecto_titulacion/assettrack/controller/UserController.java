package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.dto.CreateUser;
import com.proyecto_titulacion.assettrack.dto.UserRecord;
import com.proyecto_titulacion.assettrack.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserRecord>> getAllUsers (Pageable pageable) {
        try {
            Page<UserRecord> userRecordPage = this.userService.getAllUsers(pageable);
            if (!userRecordPage.hasContent()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(userRecordPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserRecord> getUser(@PathVariable("id") Long id) {
        try {
            UserRecord user = this.userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PatchMapping("/{id}/disable")
    public ResponseEntity<UserRecord> disableUser(@PathVariable("id") Long id) {
        try {
            UserRecord disabledUser = this.userService.disableUser(id);
            return ResponseEntity.ok(disabledUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRecord> updateUser(@PathVariable("id") Long id, @RequestBody CreateUser userDetails) {
        try {
            UserRecord updatedUser = this.userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

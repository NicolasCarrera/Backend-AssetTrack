package com.proyecto_titulacion.assettrack.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto_titulacion.assettrack.model.dto.IdentityDocumentDTO;
import com.proyecto_titulacion.assettrack.model.dto.UserDTO;
import com.proyecto_titulacion.assettrack.model.dto.CreateUser;
import com.proyecto_titulacion.assettrack.model.entity.IdentityDocument;
import com.proyecto_titulacion.assettrack.model.entity.RoleEntity;
import com.proyecto_titulacion.assettrack.model.entity.UserEntity;
import com.proyecto_titulacion.assettrack.repository.RoleRepository;
import com.proyecto_titulacion.assettrack.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCrypt.Hasher passwordHasher;
    @Autowired
    private BCrypt.Verifyer passwordVerifier;

    @Override
    public String loginUser(String identification, String password) throws JsonProcessingException {
        UserEntity user = this.getUserByCredentials(identification, identification);

        if (passwordVerifier.verify(password.toCharArray(), user.getPassword()).verified) {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(UserDTO.toUserDTO(user));
            return Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
        }

        return null;
    }

    @Override
    public Page<UserDTO> getAllUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UserEntity> userEntities = this.userRepository.getAllUsers(pageRequest);
        return userEntities.map(UserDTO::toUserDTO);
    }

    @Override
    public List<UserDTO> getUsersByRole(String role) {
        List<UserEntity> userEntities = this.userRepository.getUsersByRole(role);
        return userEntities.isEmpty() ? Collections.emptyList() : userEntities.stream().map(UserDTO::toUserDTO).toList();
    }

    @Override
    public Page<UserDTO> getUsersByFilter(String filter, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UserEntity> userEntities = this.userRepository.getUsersByFilter(filter, pageRequest);
        return userEntities.map(UserDTO::toUserDTO);
    }

    @Override
    public UserDTO createUser(CreateUser createUser) {
        RoleEntity roleEntity = this.roleRepository.findRoleByName(createUser.role()).orElse(null);
        UserEntity userEntity = UserEntity.builder()
                .firstName(createUser.firstName())
                .lastName(createUser.lastName())
                .email(createUser.email())
                .password(this.passwordHasher.hashToString(12, createUser.password().toCharArray()))
                .phone(createUser.phone())
                .identityDocuments(createUser.document().toIdentityDocument())
                .role(roleEntity)
                .status(createUser.status())
                .build();

        userEntity.setIdentityDocuments(createUser.document().toIdentityDocument());
        return UserDTO.toUserDTO(this.userRepository.save(userEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        UserEntity user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        return UserDTO.toUserDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, CreateUser updateUser) {
        RoleEntity roleEntity = this.roleRepository.findRoleByName(updateUser.role()).orElse(null);
        UserEntity userEntity = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        userEntity.setFirstName(updateUser.firstName());
        userEntity.setLastName(updateUser.lastName());
        userEntity.setEmail(updateUser.email());
        userEntity.setPhone(updateUser.phone());

        IdentityDocument identityDocument = userEntity.getIdentityDocuments();
        identityDocument.setDocument(updateUser.document().document());
        identityDocument.setIdentification(updateUser.document().identification());

        userEntity.setIdentityDocuments(identityDocument);

        if (roleEntity != null) {
            userEntity.setRole(roleEntity);
        }
        userEntity.setStatus(updateUser.status());

        return UserDTO.toUserDTO(this.userRepository.save(userEntity));
    }

    @Override
    public void deleteUsers(Long id) {
        this.userRepository.deleteUsers(id);
    }

    private UserEntity getUserByCredentials(String email, String identification) {
        Optional<UserEntity> user = this.userRepository.findByEmail(email);
        return user.orElseGet(() -> this.userRepository.findByIdentification(identification).orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con las credenciales proporcionadas.")));
    }

}


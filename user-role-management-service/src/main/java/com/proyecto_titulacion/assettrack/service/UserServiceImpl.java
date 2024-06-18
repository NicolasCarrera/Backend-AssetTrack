package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.*;
import com.proyecto_titulacion.assettrack.model.*;
import com.proyecto_titulacion.assettrack.repository.UserRepository;
import com.proyecto_titulacion.assettrack.util.JwtUtils;
import com.proyecto_titulacion.assettrack.util.RoleUtil;
import com.proyecto_titulacion.assettrack.util.UserUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<UserRecord> getAllUsers(Pageable pageable) {
        Page<UserEntity> userPage = this.userRepository.findAll(pageable);
        List<UserRecord> userRecordList = userPage.getContent().stream().map(UserUtil::toRecord).toList();
        return new PageImpl<>(userRecordList, pageable, userPage.getTotalElements());
    }

    @Override
    public UserRecord getUserById(Long id) {
        UserEntity user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        return UserUtil.toRecord(user);
    }

    @Override
    public UserRecord createUser(CreateUser createUser) {
        Set<RoleEntity> roles = this.roleService.getRolesByRoleName(createUser.roles()).stream().map(RoleUtil::toEntity).collect(Collectors.toSet());

        if (roles.isEmpty()) {
            throw new IllegalArgumentException("Los roles especificados no existen.");
        }

        UserEntity user = new UserEntity();

        user.setPassword(passwordEncoder.encode(createUser.password()));

        user.setNames(createUser.names());
        user.setLastName(createUser.lastName());
        user.setEmail(createUser.email());
        user.setPhoneNumber(createUser.phoneNumber());

        List<IdentityDocument> identityDocumentList = createUser.identityDocuments().stream()
                .map(document -> {
                    IdentityDocument identityDocument = new IdentityDocument();
                    identityDocument.setDocumentType(document.documentType());
                    identityDocument.setIdentification(document.identification());
                    identityDocument.setUser(user);
                    return identityDocument;
                })
                .collect(Collectors.toList());

        user.setIdentityDocuments(identityDocumentList);

        user.setRoles(roles);

        user.setStatus(createUser.status());

        UserEntity savedUser = this.userRepository.save(user);

        return UserUtil.toRecord(savedUser);
    }

    @Override
    public UserRecord updateUser(Long id, CreateUser userDetails) {
        UserEntity user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        user.setNames(userDetails.names());
        user.setLastName(userDetails.lastName());
        user.setEmail(userDetails.email());
        user.setPhoneNumber(userDetails.phoneNumber());

        Set<RoleEntity> roles = this.roleService.getRolesByRoleName(userDetails.roles()).stream().map(RoleUtil::toEntity).collect(Collectors.toSet());

        user.getRoles().clear();
        user.setRoles(roles);

        for (int i = 0; i < userDetails.identityDocuments().size(); i++) {
            user.getIdentityDocuments().get(i).setDocumentType(userDetails.identityDocuments().get(i).documentType());
            user.getIdentityDocuments().get(i).setIdentification(userDetails.identityDocuments().get(i).identification());
        }

        user.setStatus(userDetails.status());

        return UserUtil.toRecord(this.userRepository.save(user));
    }

    @Override
    public UserRecord disableUser(Long id) {
        UserEntity user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        user.setStatus(Status.INACTIVE);

        return UserUtil.toRecord(userRepository.save(user));
    }

    @Override
    public AuthenticationResponse loginUser(AuthenticationLogin userCredentials) {
        String identification = userCredentials.identification();
        String password = userCredentials.password();

        Authentication authentication = this.authenticateUser(identification, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToke = this.jwtUtils.createToken(authentication);

        return new AuthenticationResponse(identification, "User loged succesfully", accessToke, true);
    }

    @Override
    public Authentication authenticateUser(String identification, String password) {
        UserDetails userDetails = this.loadUserByUsername(identification);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByIdentification(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con la identificación: " + username));

        Set<String> roles = user.getRoles().stream().map(RoleEntity::getRoleName).collect(Collectors.toSet());
        Set<String> permissions = user.getRoles().stream().flatMap(role -> role.getPermissions().stream()).map(PermissionEntity::getPermissionName).collect(Collectors.toSet());

        List<GrantedAuthority> authorities = new ArrayList<>();

        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role))));
        permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));

        return new User(user.getIdentityDocuments().get(0).getIdentification(), user.getPassword(), authorities);
    }

}

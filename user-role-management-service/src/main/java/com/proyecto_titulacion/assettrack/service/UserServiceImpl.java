package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.dto.*;
import com.proyecto_titulacion.assettrack.model.PermissionEntity;
import com.proyecto_titulacion.assettrack.model.RoleEntity;
import com.proyecto_titulacion.assettrack.model.Status;
import com.proyecto_titulacion.assettrack.model.UserEntity;
import com.proyecto_titulacion.assettrack.repository.UserRepository;
import com.proyecto_titulacion.assettrack.util.JwtUtils;
import com.proyecto_titulacion.assettrack.util.RoleUtil;
import com.proyecto_titulacion.assettrack.util.UserUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
        user.setUsername(createUser.username());
        user.setPassword(passwordEncoder.encode(createUser.password()));
        user.setRoles(roles);

        UserEntity savedUser = this.userRepository.save(user);

        return UserUtil.toRecord(savedUser);
    }

    @Override
    public UserRecord updateUser(Long id, UserRecord userDetails) {
        UserEntity user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        user.setUsername(userDetails.username());
        user.setStatus(userDetails.status());

        user.getRoles().clear();
        user.setRoles(userDetails.roles().stream().map(RoleUtil::toEntity).collect(Collectors.toSet()));

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
        String username = userCredentials.username();
        String password = userCredentials.password();

        Authentication authentication = this.authenticateUser(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToke = this.jwtUtils.createToken(authentication);

        return new AuthenticationResponse(username, "User loged succesfully", accessToke, true);
    }

    @Override
    public Authentication authenticateUser(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
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
        UserEntity user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con username: " + username));

        Set<String> roles = user.getRoles().stream().map(RoleEntity::getRoleName).collect(Collectors.toSet());
        Set<String> permissions = user.getRoles().stream().flatMap(role -> role.getPermissions().stream()).map(PermissionEntity::getPermissionName).collect(Collectors.toSet());

        List<GrantedAuthority> authorities = new ArrayList<>();

        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role))));
        permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

}

package com.proyecto_titulacion.assettrack.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.proyecto_titulacion.assettrack.model.entity.IdentityDocument;
import com.proyecto_titulacion.assettrack.model.entity.PermissionEntity;
import com.proyecto_titulacion.assettrack.model.entity.RoleEntity;
import com.proyecto_titulacion.assettrack.model.entity.UserEntity;
import com.proyecto_titulacion.assettrack.model.type.DocumentType;
import com.proyecto_titulacion.assettrack.model.type.StatusType;
import com.proyecto_titulacion.assettrack.repository.PermissionRepository;
import com.proyecto_titulacion.assettrack.repository.RoleRepository;
import com.proyecto_titulacion.assettrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@Transactional
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private BCrypt.Hasher passwordHasher;

    @Override
    public void run(String... args) throws Exception {
        initializeRolesAndPermissions();
        initializeAdminUser();
    }

    private void initializeRolesAndPermissions() {
        if (roleRepository.count() == 0) {
            // Crear permisos
            PermissionEntity manageUsers = permissionRepository.save(PermissionEntity.builder().name("MANAGE_USERS").build());
            PermissionEntity manageAssets = permissionRepository.save(PermissionEntity.builder().name("MANAGE_ASSETS").build());
            PermissionEntity performMaintenance = permissionRepository.save(PermissionEntity.builder().name("PERFORM_MAINTENANCE").build());
            PermissionEntity viewReports = permissionRepository.save(PermissionEntity.builder().name("VIEW_REPORTS").build());
            PermissionEntity requestMaintenance = permissionRepository.save(PermissionEntity.builder().name("REQUEST_MAINTENANCE").build());
            PermissionEntity viewAssets = permissionRepository.save(PermissionEntity.builder().name("VIEW_ASSETS").build());

            // Crear roles y asignar permisos
            RoleEntity roleAdmin = RoleEntity.builder().name("Gerente de Mantenimiento").permissions(Set.of(manageUsers, manageAssets, performMaintenance, viewReports, viewAssets)).build();
            RoleEntity roleTechnician = RoleEntity.builder().name("Técnico de Mantenimiento").permissions(Set.of(performMaintenance, viewReports, viewAssets)).build();
            RoleEntity roleUser = RoleEntity.builder().name("Usuario").permissions(Set.of(requestMaintenance, viewAssets)).build();
            roleRepository.save(roleAdmin);
            roleRepository.save(roleTechnician);
            roleRepository.save(roleUser);
        }
    }

    private void initializeAdminUser() {
        String adminEmail = "admin@email.com";
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            RoleEntity adminRole = roleRepository.findRoleByName("Gerente de Mantenimiento")
                    .orElseThrow(() -> new IllegalStateException("Rol 'Gerente de Mantenimiento' no encontrado"));

            UserEntity adminUser = new UserEntity();
            adminUser.setFirstName("Admin");
            adminUser.setLastName("Sistema");
            adminUser.setEmail(adminEmail);
            adminUser.setPassword(passwordHasher.hashToString(12, "admin".toCharArray()));
            adminUser.setPhone("0999999999");
            adminUser.setIdentityDocuments(IdentityDocument.builder().document(DocumentType.IDENTITY_CARD).identification("admin").build());
            adminUser.setRole(adminRole);
            adminUser.setStatus(StatusType.ACTIVE);

            userRepository.save(adminUser);
        }
    }
}

package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("SELECT r FROM RoleEntity r WHERE r.roleName IN :roleNames")
    Set<RoleEntity> findRolesByRoleName(@Param("roleNames") List<String> roleNames);
}

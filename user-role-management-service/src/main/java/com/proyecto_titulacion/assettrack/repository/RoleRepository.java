package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("SELECT r FROM RoleEntity r WHERE r.name = :name")
    Optional<RoleEntity> findRoleByName(@Param("name") String name);
}

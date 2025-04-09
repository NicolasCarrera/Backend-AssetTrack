package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    @Query("SELECT p FROM PermissionEntity p where p.name = :name")
    Optional<PermissionEntity> findByName(@Param("name") String name);
}

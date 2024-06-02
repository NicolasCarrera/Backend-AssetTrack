package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    @Query("SELECT p FROM PermissionEntity p WHERE p.permissionName IN :permitsNames")
    Set<PermissionEntity> findPermitsByPermitsNames (@Param("permitsNames") List<String> permitsNames);
}

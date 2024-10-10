package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.entity.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    @Query("SELECT a FROM Asset a WHERE a.id IN :ids AND a.status = 'ACTIVE'")
    Page<Asset> findAssetsByIds(@Param("ids") List<Long> ids, Pageable pageable);

    @Query("SELECT a FROM Asset a WHERE a.branchId = :branchId AND a.status = 'ACTIVE'")
    Page<Asset> findAssetsByBranchId(@Param("branchId") Long branchId, Pageable pageable);

    @Query("SELECT a FROM Asset a WHERE a.status = 'ACTIVE' AND (" +
            "LOWER(a.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(a.brand) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(a.model) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(a.serial) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(a.location) LIKE LOWER(CONCAT('%', :filter, '%')))")
    Page<Asset> searchAssetsByFilter(@Param("filter") String filter, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Asset a SET a.status = 'INACTIVE' WHERE a.id = :id")
    void deleteAsset(@Param("id") Long id);

    @Query("SELECT COUNT(a) FROM Asset a WHERE a.branchId = :branchId")
    Integer countByBranchId(@Param("branchId") Long branchId);
}

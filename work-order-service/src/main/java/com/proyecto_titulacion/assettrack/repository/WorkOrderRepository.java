package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.entity.WorkOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    @Query("SELECT MIN(m.date) FROM WorkOrder m WHERE m.assetId = :assetId")
    Optional<LocalDate> findNextMaintenanceDateByAssetId(@Param("assetId") Long assetId);

    @Query("SELECT w FROM WorkOrder w WHERE w.assetId = :assetId")
    Page<WorkOrder> getWorkOrdersByAssetId(@Param("assetId") Long assetId, PageRequest pageRequest);

    @Query("SELECT w FROM WorkOrder w WHERE w.userId = :userId")
    Page<WorkOrder> getWorkOrdersByUserId(@Param("userId") Long userId, PageRequest pageRequest);

    @Query("SELECT w.assetId FROM WorkOrder w WHERE w.branchId = :branchId")
    List<Long> getAssetIdsByBranchId(@Param("branchId") Long branchId);
}

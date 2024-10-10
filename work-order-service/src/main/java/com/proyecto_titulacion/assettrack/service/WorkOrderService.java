package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.entity.WorkOrder;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface WorkOrderService {
    Page<WorkOrder> getAllWorkOrders(int page, int size);

    Page<WorkOrder> getWorkOrdersByAssetId(Long assetId, int page, int size);

    Page<WorkOrder> getWorkOrdersByUserId(Long userId, int page, int size);

    WorkOrder cleateWorkOrder(WorkOrder cleateWorkOrder);

    WorkOrder updateWorkOrder(Long id, WorkOrder updateWorkOrder);

    void deleteWorkOrder(Long id);

    LocalDate getNextMaintenanceDateByAssetId(Long assetId);

    List<Long> getAssetIdsByBranchId(Long branchId);
}

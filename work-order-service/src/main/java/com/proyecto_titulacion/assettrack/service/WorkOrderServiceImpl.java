package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.entity.WorkOrder;
import com.proyecto_titulacion.assettrack.repository.WorkOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {
    @Autowired
    protected WorkOrderRepository workOrderRepository;

    @Override
    public Page<WorkOrder> getAllWorkOrders(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.workOrderRepository.findAll(pageRequest);
    }

    @Override
    public Page<WorkOrder> getWorkOrdersByAssetId(Long assetId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.workOrderRepository.getWorkOrdersByAssetId(assetId, pageRequest);
    }

    @Override
    public Page<WorkOrder> getWorkOrdersByUserId(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.workOrderRepository.getWorkOrdersByUserId(userId, pageRequest);
    }

    @Override
    public WorkOrder cleateWorkOrder(WorkOrder cleateWorkOrder) {
        return this.workOrderRepository.save(cleateWorkOrder);
    }

    @Override
    public WorkOrder updateWorkOrder(Long id, WorkOrder updateWorkOrder) {
        WorkOrder workOrder = this.workOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden de Trabajo  no encontrado con ID: " + id));
        updateWorkOrder.setId(workOrder.getId());
        return this.workOrderRepository.save(updateWorkOrder);
    }

    @Override
    public void deleteWorkOrder(Long id) {
        WorkOrder workOrder = this.workOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden de Trabajo  no encontrado con ID: " + id));
        this.workOrderRepository.delete(workOrder);
    }

    @Override
    public LocalDate getNextMaintenanceDateByAssetId(Long assetId) {
        return this.workOrderRepository.findNextMaintenanceDateByAssetId(assetId).orElse(null);
    }

    @Override
    public List<Long> getAssetIdsByBranchId(Long branchId) {
        return this.workOrderRepository.getAssetIdsByBranchId(branchId);
    }
}

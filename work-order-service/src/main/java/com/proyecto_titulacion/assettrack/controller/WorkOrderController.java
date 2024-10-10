package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.model.entity.WorkOrder;
import com.proyecto_titulacion.assettrack.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WorkOrderController {
    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping
    public ResponseEntity<Page<WorkOrder>> getAllWorkOrders(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<WorkOrder> workOrders = this.workOrderService.getAllWorkOrders(page, size);
        return ResponseEntity.ok(workOrders);
    }

    @GetMapping("/assets/{assetId}")
    public ResponseEntity<Page<WorkOrder>> getWorkOrdersByAssetId(
            @PathVariable("assetId") Long assetId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<WorkOrder> workOrders = this.workOrderService.getWorkOrdersByAssetId(assetId, page, size);
        return ResponseEntity.ok(workOrders);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<Page<WorkOrder>> getWorkOrdersByUserId(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<WorkOrder> workOrders = this.workOrderService.getWorkOrdersByUserId(userId, page, size);
        return ResponseEntity.ok(workOrders);
    }

    @PostMapping
    public ResponseEntity<WorkOrder> cleateWorkOrder(@RequestBody WorkOrder cleateWorkOrder) {
        WorkOrder workOrder = this.workOrderService.cleateWorkOrder(cleateWorkOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(workOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkOrder> updateWorkOrder(@PathVariable("id") Long id, @RequestBody WorkOrder updateWorkOrder) {
        WorkOrder workOrder = this.workOrderService.updateWorkOrder(id, updateWorkOrder);
        return ResponseEntity.ok(workOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkOrder(@PathVariable("id") Long id) {
        try {
            this.workOrderService.deleteWorkOrder(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/assets/{assetId}/next-maintenance-date")
    public ResponseEntity<LocalDate> getNextMaintenanceDateByAssetId(@PathVariable("assetId") Long assetId) {
        return ResponseEntity.ok(this.workOrderService.getNextMaintenanceDateByAssetId(assetId));
    }

    @GetMapping("/branches/{branchId}/asset-ids")
    public List<Long> getAssetIdsByBranchId(@PathVariable("branchId") Long branchId) {
        return this.workOrderService.getAssetIdsByBranchId(branchId);
    }
}

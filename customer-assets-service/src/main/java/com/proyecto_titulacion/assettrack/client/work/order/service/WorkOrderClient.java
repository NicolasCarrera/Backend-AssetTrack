package com.proyecto_titulacion.assettrack.client.work.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "work-order-service")
public interface WorkOrderClient {
    @GetMapping("/api/v1/work-orders/assets/{assetId}/next-maintenance-date")
    LocalDate getNextMaintenanceDateByAssetId(@PathVariable("assetId") Long AssetId);
    @GetMapping("/api/v1/work-orders/branches/{branchId}/asset-ids")
    List<Long> getAssetIdsByBranchId(@PathVariable("branchId") Long branchId);
}

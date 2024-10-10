package com.proyecto_titulacion.assettrack.client.asset.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer-assets-service")
public interface AssetClient {
    @GetMapping("/api/v1/assets/branch/{branchId}/count")
    Integer countAssetsByBranchId(@PathVariable("branchId") Long branchId);
}

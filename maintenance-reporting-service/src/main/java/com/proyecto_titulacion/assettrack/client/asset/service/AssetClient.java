package com.proyecto_titulacion.assettrack.client.asset.service;

import com.proyecto_titulacion.assettrack.client.asset.model.AssetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-assets-service")
public interface AssetClient {
    @GetMapping("/api/v1/assets/{id}")
    AssetDTO getAssetById(@PathVariable("id") Long id);
}

package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.dto.AssetDTO;
import com.proyecto_titulacion.assettrack.model.entity.Asset;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AssetService {
    Page<AssetDTO> getAssetsInMaintenanceByBranchId(Long branchId, int page, int size);
    Page<AssetDTO> getAssetsByBranchId(Long branchId, int page, int size);
    Page<AssetDTO> getAssetsByFilter(String filter, int page, int size);
    AssetDTO getAssetById(Long id);
    AssetDTO createAsset(Asset createAsset);
    AssetDTO updateAsset(Long id, Asset asset);
    void deleteAsset(Long id);
    Integer countAssetsByBranchId(Long branchId);

    AssetDTO updateAssetFiles(Long assetId, MultipartFile image, List<MultipartFile> documents) throws Exception;
}

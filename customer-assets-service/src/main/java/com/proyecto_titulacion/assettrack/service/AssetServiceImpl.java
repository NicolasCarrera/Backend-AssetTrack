package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.client.maintenance.report.service.MaintenanceReportClient;
import com.proyecto_titulacion.assettrack.client.work.order.service.WorkOrderClient;
import com.proyecto_titulacion.assettrack.model.dto.AssetDTO;
import com.proyecto_titulacion.assettrack.model.dto.MaintenanceDTO;
import com.proyecto_titulacion.assettrack.model.entity.Asset;
import com.proyecto_titulacion.assettrack.repository.AssetRepository;
import com.proyecto_titulacion.assettrack.util.feign.FeignUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private MaintenanceReportClient maintenanceReportClient;
    @Autowired
    private WorkOrderClient workOrderClient;

    @Override
    public AssetDTO getAssetById(Long id) {
        Asset asset = this.assetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activo no encontrado con ID: " + id));
        MaintenanceDTO maintenanceDTO = getMaintenanceDTO(asset.getId());
        return AssetDTO.toAssetDTO(asset, maintenanceDTO);
    }

    @Override
    @Transactional
    public AssetDTO createAsset(Asset createAsset) {
        Asset asset = this.assetRepository.save(createAsset);
        return AssetDTO.toAssetDTO(asset, null);
    }

    @Override
    public AssetDTO updateAsset(Long id, Asset assetDetails) {
        Asset asset = this.assetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activo no encontrado con ID: " + id));
        assetDetails.setId(asset.getId());
        Asset updatedAsset = this.assetRepository.save(assetDetails);
        return AssetDTO.toAssetDTO(updatedAsset, null);
    }

    @Override
    public void deleteAsset(Long id) {
        this.assetRepository.deleteAsset(id);
    }

    @Override
    public Page<AssetDTO> getAssetsInMaintenanceByBranchId(Long branchId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Long> ids = this.workOrderClient.getAssetIdsByBranchId(branchId);
        Page<Asset> assets = this.assetRepository.findAssetsByIds(ids, pageRequest);
        return assets.map(asset -> {
            MaintenanceDTO maintenanceDTO = getMaintenanceDTO(asset.getId());
            return AssetDTO.toAssetDTO(asset, maintenanceDTO);
        });
    }

    @Override
    public Page<AssetDTO> getAssetsByBranchId(Long branchId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Asset> assets = this.assetRepository.findAssetsByBranchId(branchId, pageRequest);
        return assets.map(asset -> {
            MaintenanceDTO maintenanceDTO = getMaintenanceDTO(asset.getId());
            return AssetDTO.toAssetDTO(asset, maintenanceDTO);
        });
    }

    @Override
    public Page<AssetDTO> getAssetsByFilter(String filter, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Asset> assets = this.assetRepository.searchAssetsByFilter(filter, pageRequest);
        return assets.map(asset -> {
            MaintenanceDTO maintenanceDTO = getMaintenanceDTO(asset.getId());
            return AssetDTO.toAssetDTO(asset, maintenanceDTO);
        });
    }

    @Override
    public Integer countAssetsByBranchId(Long branchId) {
        return this.assetRepository.countByBranchId(branchId);
    }

    @Override
    @Transactional
    public AssetDTO updateAssetFiles(Long assetId, MultipartFile image, List<MultipartFile> documents) throws Exception {
        Asset asset = this.assetRepository.findById(assetId)
                .orElseThrow(() -> new EntityNotFoundException("Activo no encontrado con ID: " + assetId));

        // Subir y guardar la imagen
        if (image != null) {
            String imagePath = this.fileService.uploadFile(image, "images/" + image.getOriginalFilename());
            asset.setImagePath(imagePath);
        }

        // Subir y guardar documentos asociados
        List<String> assetFiles = new ArrayList<>();
        if (documents != null && !documents.isEmpty()) {
            for (MultipartFile document : documents) {
                String filePath = this.fileService.uploadFile(document, "documents/" + document.getOriginalFilename());
                assetFiles.add(filePath);
            }
            asset.setFiles(assetFiles);
        }
        Asset updatedAsset = this.assetRepository.save(asset);
        return AssetDTO.toAssetDTO(updatedAsset, null);
    }

    private MaintenanceDTO getMaintenanceDTO(Long assetId) {
        LocalDate lastMaintenance = FeignUtil.safeFeignCall(() ->
                this.maintenanceReportClient.getLastMaintenanceDateByAssetId(assetId)
        );

        LocalDate nextMaintenance = FeignUtil.safeFeignCall(() ->
                this.workOrderClient.getNextMaintenanceDateByAssetId(assetId)
        );

        return new MaintenanceDTO(lastMaintenance, nextMaintenance);
    }
}

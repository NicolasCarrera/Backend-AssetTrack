package com.proyecto_titulacion.assettrack.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.proyecto_titulacion.assettrack.model.entity.Asset;
import com.proyecto_titulacion.assettrack.model.type.StatusType;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AssetDTO(
        Long id,
        String imagePath,
        String name,
        String description,
        String brand,
        String model,
        String serial,
        LocalDate acquisition,
        String location,
        StatusType status,
        Long companyId,
        Long branchId,
        List<String> files,
        MaintenanceDTO maintenance
) {
    public static AssetDTO toAssetDTO(Asset asset, MaintenanceDTO maintenance) {
        return new AssetDTO(
                asset.getId(),
                asset.getImagePath(),
                asset.getName(),
                asset.getDescription(),
                asset.getBrand(),
                asset.getModel(),
                asset.getSerial(),
                asset.getAcquisition(),
                asset.getLocation(),
                asset.getStatus(),
                asset.getCompanyId(),
                asset.getBranchId(),
                asset.getFiles(),
                maintenance
        );
    }
    public Asset toAsset(){
        return Asset.builder()
                .id(this.id)
                .imagePath(this.imagePath)
                .name(this.name)
                .description(this.description)
                .brand(this.brand)
                .model(this.model)
                .serial(this.serial)
                .acquisition(this.acquisition)
                .location(this.location)
                .status(this.status)
                .companyId(this.companyId)
                .branchId(this.branchId)
                .files(this.files)
                .build();
    }
}

package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.client.asset.service.AssetClient;
import com.proyecto_titulacion.assettrack.model.entity.Branch;
import com.proyecto_titulacion.assettrack.repository.BranchRepository;
import com.proyecto_titulacion.assettrack.util.feign.FeignUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private AssetClient assetClient;

    @Override
    public Page<Branch> getAllBranches(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Branch> branches = this.branchRepository.getAllBranches(pageRequest);
        return branches.map(branch -> {
            Integer assetsCount = FeignUtil.safeFeignCall(() ->
                    this.assetClient.countAssetsByBranchId(branch.getId())
            );
            branch.setAssets(assetsCount);
            return branch;
        });
    }

    @Override
    public Page<Branch> getBranchesByCompanyId(Long companyId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Branch> branches = this.branchRepository.getBranchesByCompanyId(companyId, pageRequest);
        return branches.map(branch -> {
            Integer assetsCount = FeignUtil.safeFeignCall(() ->
                    this.assetClient.countAssetsByBranchId(branch.getId())
            );
            branch.setAssets(assetsCount);
            return branch;
        });
    }

    @Override
    public Branch createBranch(Branch createBranch) {
        return this.branchRepository.save(createBranch);
    }

    @Override
    public Branch getBranchById(Long id) {
        return this.branchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrado con ID: " + id));
    }

    @Override
    public Branch updateBranch(Long id, Branch updateBranch) {
        Branch branch = this.branchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrado con ID: " + id));
        updateBranch.setId(branch.getId());
        return this.branchRepository.save(updateBranch);
    }

    @Override
    public void deleteBranch(Long id) {
        this.branchRepository.deleteBranch(id);
    }
}

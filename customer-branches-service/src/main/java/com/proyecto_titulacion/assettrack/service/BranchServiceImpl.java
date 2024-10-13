package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.client.asset.service.AssetClient;
import com.proyecto_titulacion.assettrack.model.dto.CreateBranch;
import com.proyecto_titulacion.assettrack.model.entity.Branch;
import com.proyecto_titulacion.assettrack.model.entity.Company;
import com.proyecto_titulacion.assettrack.repository.BranchRepository;
import com.proyecto_titulacion.assettrack.repository.CompanyRepository;
import com.proyecto_titulacion.assettrack.util.feign.FeignUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private CompanyRepository companyRepository;
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
    public Branch createBranch(CreateBranch createBranch) {
        Company company = this.companyRepository.findById(createBranch.companyId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrado con ID: " + createBranch.companyId()));
        return this.branchRepository.save(Branch.builder()
                .name(createBranch.name())
                .location(createBranch.location())
                .email(createBranch.email())
                .phone(createBranch.phone())
                .status(createBranch.status())
                .company(company)
                .build());
    }

    @Override
    public Branch getBranchById(Long id) {
        return this.branchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrado con ID: " + id));
    }

    @Override
    public Optional<Branch> updateBranch(Long id, CreateBranch branchDetails) {
        Optional<Company> company = this.companyRepository.findById(branchDetails.companyId());
        return this.branchRepository.findById(id)
                .map(branch -> {
                    branch.setName(branchDetails.name());
                    branch.setLocation(branchDetails.location());
                    branch.setEmail(branchDetails.email());
                    branch.setPhone(branchDetails.phone());
                    branch.setStatus(branchDetails.status());
                    branch.setCompany(company.orElse(branch.getCompany()));
                    return this.branchRepository.save(branch);
                });
    }

    @Override
    public void deleteBranch(Long id) {
        this.branchRepository.deleteBranch(id);
    }
}

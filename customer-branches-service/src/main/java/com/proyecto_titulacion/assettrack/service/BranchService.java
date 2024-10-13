package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.dto.CreateBranch;
import com.proyecto_titulacion.assettrack.model.entity.Branch;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BranchService {
    Branch getBranchById(Long id);

    Page<Branch> getBranchesByCompanyId(Long companyId, int page, int size);

    Page<Branch> getAllBranches(int page, int size);

    Branch createBranch(CreateBranch createBranch);

    Optional<Branch> updateBranch(Long id, CreateBranch branchDetails);

    void deleteBranch(Long id);
}

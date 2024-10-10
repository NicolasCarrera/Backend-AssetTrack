package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.entity.Branch;
import org.springframework.data.domain.Page;

public interface BranchService {
    Branch getBranchById(Long id);

    Page<Branch> getBranchesByCompanyId(Long companyId, int page, int size);

    Page<Branch> getAllBranches(int page, int size);

    Branch createBranch(Branch createBranch);

    Branch updateBranch(Long id, Branch updateBranch);

    void deleteBranch(Long id);
}

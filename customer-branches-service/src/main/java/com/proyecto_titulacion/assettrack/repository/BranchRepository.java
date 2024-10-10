package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.entity.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    @Query("SELECT b FROM Branch b WHERE b.status = 'ACTIVE'")
    Page<Branch> getAllBranches(PageRequest pageRequest);

    @Query("SELECT b FROM Branch b INNER JOIN b.company c WHERE c.status = 'ACTIVE' AND c.id = :companyId")
    Page<Branch> getBranchesByCompanyId(@Param("companyId") Long companyId, PageRequest pageRequest);

    @Modifying
    @Transactional
    @Query("UPDATE Branch b SET b.status = 'INACTIVE' WHERE b.id = :id")
    void deleteBranch(Long id);
}

package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>  {
    @Query("SELECT c FROM Company c WHERE c.status = 'ACTIVE'")
    Page<Company> findAllCompanies(PageRequest pageRequest);
    @Query("SELECT c FROM Company c WHERE c.status = 'ACTIVE' AND " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Company> findCompaniesByName(@Param("name") String name, PageRequest pageRequest);

    @Modifying
    @Transactional
    @Query("UPDATE Company c SET c.status = 'INACTIVE' WHERE c.id = :id")
    void deleteByCompany(@Param("id") Long id);

    @Query("SELECT c FROM Company c WHERE c.status = 'ACTIVE' AND c.userId = :userId")
    Company findByCompanyByUserId(@Param("userId") Long userId);
}

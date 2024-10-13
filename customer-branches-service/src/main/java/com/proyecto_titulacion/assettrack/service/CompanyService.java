package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.dto.CreateCompany;
import com.proyecto_titulacion.assettrack.model.entity.Company;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CompanyService {

    Page<Company> getAllCompanies(int page, int size);

    Page<Company> getCompaniesByName(String name, int page, int size);

    Company getCompanyByUserId(Long userId);

    Company createCompany(CreateCompany createCompany);

    Company getCompanyById(Long id);

    Optional<Company> updateCompany(Long id, CreateCompany companyDetails);

    void deleteCompany(Long id);
}

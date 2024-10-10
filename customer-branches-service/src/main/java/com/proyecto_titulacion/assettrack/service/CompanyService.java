package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.entity.Company;
import org.springframework.data.domain.Page;

public interface CompanyService {

    Page<Company> getAllCompanies(int page, int size);

    Page<Company> getCompaniesByName(String name, int page, int size);

    Company getCompanyByUserId(Long userId);

    Company createCompany(Company createCompany);

    Company getCompanyById(Long id);

    Company updateCompany(Long id, Company updateCompany);

    void deleteCompany(Long id);
}

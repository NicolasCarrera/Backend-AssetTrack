package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.dto.CreateCompany;
import com.proyecto_titulacion.assettrack.model.entity.Company;
import com.proyecto_titulacion.assettrack.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Page<Company> getAllCompanies(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.companyRepository.findAllCompanies(pageRequest);
    }

    @Override
    public Page<Company> getCompaniesByName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.companyRepository.findCompaniesByName(name, pageRequest);
    }

    @Override
    public Company getCompanyById(Long id){
        return this.companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + id));
    }

    @Override
    public Optional<Company> updateCompany(Long id, CreateCompany companyDetails) {
        return this.companyRepository.findById(id)
                .map(company -> {
                    company.setName(companyDetails.name());
                    company.setUserId(companyDetails.userId());
                    company.setStatus(companyDetails.status());
                    return this.companyRepository.save(company);
                });
    }

    @Override
    public void deleteCompany(Long id) {
        this.companyRepository.deleteByCompany(id);
    }

    @Override
    public Company getCompanyByUserId(Long userId) {
        return this.companyRepository.findByCompanyByUserId(userId);
    }

    @Override
    public Company createCompany(CreateCompany createCompany) {
        return this.companyRepository.save(Company.builder()
                        .name(createCompany.name())
                        .userId(createCompany.userId())
                        .status(createCompany.status())
                .build());
    }
}

package com.proyecto_titulacion.assettrack.client.customer.service;

import com.proyecto_titulacion.assettrack.client.customer.model.BranchDTO;
import com.proyecto_titulacion.assettrack.client.customer.model.CompanyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-branches-service")
public interface CompanyBranchClient {
    @GetMapping("/api/v1/customers/companies/{id}")
    CompanyDTO getCompanyById(@PathVariable("id") Long id);
    @GetMapping("/api/v1/customers/branches/{id}")
    BranchDTO getBranchById(@PathVariable("id") Long id);
}

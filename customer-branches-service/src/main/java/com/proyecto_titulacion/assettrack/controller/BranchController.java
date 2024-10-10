package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.model.entity.Branch;
import com.proyecto_titulacion.assettrack.service.BranchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
@CrossOrigin(origins = "*")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping
    public ResponseEntity<Page<Branch>> getAllBranches(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Branch> branches = this.branchService.getAllBranches(page, size);
        return ResponseEntity.ok(branches);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Page<Branch>> getBranchesByCompanyId(
            @PathVariable("companyId") Long companyId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        try {
            Page<Branch> branches = this.branchService.getBranchesByCompanyId(companyId, page, size);
            if (branches.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(branches);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Branch> createBranch(@RequestBody Branch createBranch){
        Branch branch = this.branchService.createBranch(createBranch);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable("id") Long id) {
        try {
            Branch branch = this.branchService.getBranchById(id);
            return ResponseEntity.ok(branch);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable("id") Long id, Branch updateBranch) {
        Branch branch = this.branchService.updateBranch(id, updateBranch);
        return ResponseEntity.ok(branch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable("id") Long id){
        try {
            this.branchService.deleteBranch(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

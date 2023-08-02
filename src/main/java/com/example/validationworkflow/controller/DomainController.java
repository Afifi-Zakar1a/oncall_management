package com.example.validationworkflow.controller;

import com.example.validationworkflow.entities.Domain;
import com.example.validationworkflow.entities.Totals;
import com.example.validationworkflow.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")

@RequestMapping("/domains")
public class DomainController {

    @Autowired
    private DomainService domainService;



    @GetMapping
    public ResponseEntity<List<Domain>> getAllDomains() {
        List<Domain> domains = domainService.getAllDomains();

        if (!domains.isEmpty()) {
            return ResponseEntity.ok(domains);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Domain> getDomain(@PathVariable Long id) {
        Domain domain = domainService.getDomainById(id);
        if (domain != null) {
            return ResponseEntity.ok(domain);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Domain> createDomain(@RequestBody Domain domain) {
        Domain createdDomain = domainService.createDomain(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDomain);
    }

    @PostMapping("/s")
    public ResponseEntity<List<Domain>> createDomains(@RequestBody List<Domain> domains) {
        List<Domain> createdDomains   = domainService.createDomains(domains);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDomains );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Domain> updateDomain(@PathVariable Long id, @RequestBody Domain domain) {
        Domain updatedDomain = domainService.updateDomain(id, domain);
        if (updatedDomain != null) {
            return ResponseEntity.ok(updatedDomain);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomain(@PathVariable Long id) {
        boolean deleted = domainService.deleteDomain(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/totals")
    public ResponseEntity<Totals> getTotals() {
        Totals totals = domainService.calculateTotals();
        return ResponseEntity.ok(totals);
    }

    @GetMapping("/billable")
        public ResponseEntity<Integer> getBillable() {

        int billable= domainService.calculateBillable();
        return ResponseEntity.ok(billable);



    }




}

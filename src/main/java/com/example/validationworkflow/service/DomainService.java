package com.example.validationworkflow.service;

import com.example.validationworkflow.Repository.DomainRepository;
import com.example.validationworkflow.entities.Domain;
import com.example.validationworkflow.entities.Totals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service

public class DomainService {
    @Autowired
    private DomainRepository domainRepository;

    public Domain getDomainById(Long id) {
        Optional<Domain> optionalDomain = domainRepository.findById(id);
        return optionalDomain.orElse(null);
    }

    public List<Domain> getAllDomains() {
        return domainRepository.findAll();
    }

    public Domain createDomain(Domain domain) {
        // Add implementation to create and save a new domain
        return domainRepository.save(domain);    }

    public List<Domain> createDomains(List<Domain> domains) {
        return domainRepository.saveAll(domains);
    }



    public Domain updateDomain(Long id, Domain domain) {
        Optional<Domain> optionalDomain = domainRepository.findById(id);
        if (optionalDomain.isPresent()) {
            Domain existingDomain = optionalDomain.get();
            existingDomain.setDomainName(domain.getDomainName());
            existingDomain.setAchievedLastYear(domain.getAchievedLastYear());
            existingDomain.setContract(domain.getContract());
            existingDomain.setAchievedThisYear(domain.getAchievedThisYear());
            return domainRepository.save(existingDomain);
        }
        return null;
    }

    public boolean deleteDomain(Long id) {
        Optional<Domain> optionalDomain = domainRepository.findById(id);
        if (optionalDomain.isPresent()) {
            domainRepository.delete(optionalDomain.get());
            return true;
        }
        return false;
    }

    public Totals calculateTotals() {
        List<Domain> domains = domainRepository.findAll();
        int totalAchievedThisYear = 0;
        int totalContract = 0;
        int totalAchievedLastYear = 0;


        for (Domain domain : domains) {
            totalAchievedThisYear += domain.getAchievedThisYear();
            totalContract += domain.getContract();
            totalAchievedLastYear += domain.getAchievedLastYear();
        }



        return new Totals(totalAchievedThisYear, totalContract, totalAchievedLastYear);
    }
    public int calculateBillable() {
        List<Domain> domains = domainRepository.findAll();
        int totalAchievedThisYear = 0;
        int totalContract = 0;

        for (Domain domain : domains) {
            totalAchievedThisYear += domain.getAchievedThisYear();
            totalContract += domain.getContract();
        }

        return totalAchievedThisYear - totalContract;
    }



}

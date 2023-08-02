package com.example.validationworkflow.service;

import com.example.validationworkflow.Repository.InterventionRequestRepository;
import com.example.validationworkflow.entities.InterventionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcceptanceService {
    @Autowired
    private InterventionRequestRepository interventionRequestRepository;

    public InterventionRequest acceptInterventionRequest(Long id) {
        InterventionRequest interventionRequest = interventionRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intervention request not found"));

        interventionRequest.setStatus("accepted");
        return interventionRequestRepository.save(interventionRequest);
    }
}

package com.example.validationworkflow.service;

import com.example.validationworkflow.Repository.InterventionRequestRepository;
import com.example.validationworkflow.entities.InterventionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    @Autowired
    private InterventionRequestRepository interventionRequestRepository;

    public InterventionRequest validateInterventionRequest(Long id) {
        InterventionRequest interventionRequest = interventionRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intervention request not found"));


        String currentStatus = interventionRequest.getStatus();
        if (currentStatus.equals("accepted")) {
            throw new RuntimeException("Cannot change status from 'accepted' back to 'validate'");
        }

        interventionRequest.setStatus("validate");
        return interventionRequestRepository.save(interventionRequest);
    }
}

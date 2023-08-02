package com.example.validationworkflow.service;

import com.example.validationworkflow.Repository.InterventionRequestRepository;
import com.example.validationworkflow.entities.InterventionRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
public class InterventionRequestService {
    @Autowired
    private  InterventionRequestRepository interventionRequestRepository;
    /*private InterventionRequestRepository interventionRequestRepository;
    public  InterventionRequestService (InterventionRequestRepository interventionRequestRepository){
        this.interventionRequestRepository=interventionRequestRepository;
    }*/

    @Autowired
    private ValidationService validationService;


    public InterventionRequest createInterventionRequest(InterventionRequest interventionRequest) {
        interventionRequest.setCreationTime(LocalDateTime.now());
        interventionRequest.setStatus("pending");

        return interventionRequestRepository.save(interventionRequest);
    }


    public List<InterventionRequest> getAllInterventionRequests() {
        return interventionRequestRepository.findAll();
    }


    @Scheduled(fixedRate =  60 * 1000) // Runs every 1 48 H
    public void checkPendingInterventionRequests() {
        List<InterventionRequest> pendingRequests = interventionRequestRepository.findByStatus("pending");
        for (InterventionRequest request : pendingRequests) {
            LocalDateTime creationTime = request.getCreationTime();
            LocalDateTime currentTime = LocalDateTime.now();
            Duration duration = Duration.between(creationTime, currentTime);
            long minutesPassed = duration.toMinutes();

            if (minutesPassed >= 1) {
                request = validationService.validateInterventionRequest(request.getId());
                interventionRequestRepository.save(request);
            }
        }
    }

}

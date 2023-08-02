package com.example.validationworkflow.controller;

import com.example.validationworkflow.entities.InterventionRequest;
import com.example.validationworkflow.service.AcceptanceService;
import com.example.validationworkflow.service.InterventionRequestService;
import com.example.validationworkflow.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")

@RequestMapping("/intervention-requests")

@RestController

public class InterventionRequestController {
    @Autowired
    private InterventionRequestService interventionRequestService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private AcceptanceService acceptanceService;

    @GetMapping
    public ResponseEntity<List<InterventionRequest>> getAllInterventionRequests() {
        List<InterventionRequest> interventionRequests = interventionRequestService.getAllInterventionRequests();
        return ResponseEntity.ok(interventionRequests);
    }


    @PostMapping
    public ResponseEntity<InterventionRequest> createInterventionRequest(@RequestBody InterventionRequest interventionRequest) {
        InterventionRequest createdRequest = interventionRequestService.createInterventionRequest(interventionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }


    /*@PostMapping("/process-pending-requests")
    public ResponseEntity<String> processPendingRequests() {
        interventionRequestService.checkPendingInterventionRequests();
        return ResponseEntity.ok("Pending requests processed successfully");
    }*/

    @PutMapping("/{id}/validate")
    public ResponseEntity<InterventionRequest> validateInterventionRequest(@PathVariable("id") Long id) {
        InterventionRequest validatedRequest = validationService.validateInterventionRequest(id);
        return ResponseEntity.ok(validatedRequest);
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<InterventionRequest> acceptInterventionRequest(@PathVariable("id") Long id) {
        InterventionRequest acceptedRequest = acceptanceService.acceptInterventionRequest(id);
        return ResponseEntity.ok(acceptedRequest);
    }
}

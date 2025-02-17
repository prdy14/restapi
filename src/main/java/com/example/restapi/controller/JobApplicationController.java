package com.example.restapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.restapi.dto.JobApplicationDTO;
import com.example.restapi.dto.JobApplicationRequest;
import com.example.restapi.entity.JobApplication;
import com.example.restapi.service.JobApplicationService;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

  @Autowired
  private JobApplicationService jobApplicationService;

  @PostMapping
  public ResponseEntity<?> createApplication(@Valid @RequestBody JobApplicationRequest request) {
    return jobApplicationService.createApplication(request);
  }

  @GetMapping
  public ResponseEntity<?> getAllApplications(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      String username = userDetails.getUsername();
      List<JobApplication> applications = jobApplicationService.getAllApplicationsByUsername(username);
      List<JobApplicationDTO> applicationDTOs = applications.stream()
          .map(JobApplicationDTO::new)
          .collect(Collectors.toList());
      return ResponseEntity.ok(applicationDTOs);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error fetching applications: " + e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getApplicationById(@PathVariable Long id) {
    return jobApplicationService.getApplicationById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateApplication(
      @PathVariable Long id,
      @Valid @RequestBody JobApplicationRequest request) {
    return jobApplicationService.updateApplication(id, request);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
    return jobApplicationService.deleteApplication(id);
  }
}

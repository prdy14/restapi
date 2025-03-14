package com.example.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.restapi.entity.JobApplication;
import com.example.restapi.entity.User;

import java.util.List;

@Repository
public interface JobApplicationRepository
    extends JpaRepository<JobApplication, Long>, JpaSpecificationExecutor<JobApplication> {
  List<JobApplication> findByUser(User user);

  List<JobApplication> findByUserAndStatus(User user, JobApplication.ApplicationStatus status);

  List<JobApplication> findByUserAndCompanyNameContainingIgnoreCase(User user, String companyName);

  List<JobApplication> findByUserAndJobTitleContainingIgnoreCase(User user, String jobTitle);

  Long countByUser(User user);

  List<JobApplication> findByUserUsername(String username);
}

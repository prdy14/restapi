package com.example.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.restapi.entity.Interview;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
 List<Interview> findByJobApplicationId(Long jobApplicationId);

 List<Interview> findByJobApplicationIdOrderByInterviewDateAsc(Long jobApplicationId);

 Optional<Interview> findByJobApplicationIdAndRoundNumber(Long jobApplicationId, Integer roundNumber);

 List<Interview> findByJobApplicationIdAndRoundNumberGreaterThan(Long jobApplicationId, Integer roundNumber);

 @Query("SELECT MAX(i.roundNumber) FROM Interview i WHERE i.jobApplication.id = :jobApplicationId")
 Optional<Integer> findMaxRoundNumberByJobApplicationId(Long jobApplicationId);
}
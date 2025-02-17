package com.example.restapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
@Data
public class Interview {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne
 @JoinColumn(name = "job_application_id")
 private JobApplication jobApplication;

 @Column(name = "round_number")
 private Integer roundNumber;

 @Column(name = "interview_date")
 private LocalDateTime interviewDate;

 @Column(name = "interview_type")
 private String interviewType; // e.g., "Technical", "HR", "System Design"

 @Column(name = "notes")
 private String notes;

 @Column(name = "status")
 private String status; // "SCHEDULED", "COMPLETED", "CANCELLED"

}

package com.example.restapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InterviewExceptionHandler {

 @ExceptionHandler(EntityNotFoundException.class)
 public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
  Map<String, String> response = new HashMap<>();
  response.put("error", ex.getMessage());
  return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(Exception.class)
 public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
  Map<String, String> response = new HashMap<>();
  System.out.println(ex);
  response.put("error", "An unexpected error 1 occurred");
  return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 }
}
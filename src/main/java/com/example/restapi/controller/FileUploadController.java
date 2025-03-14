package com.example.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.restapi.service.FileUploadService;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

 @Autowired
 private FileUploadService fileUploadService;

 @PostMapping("/upload-resume")
 public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file) {
  System.out.println(file);
  try {
   String fileUrl = fileUploadService.uploadResume(file);
   return ResponseEntity.ok(fileUrl);
  } catch (IOException e) {
   return ResponseEntity.badRequest().body("File upload failed");
  }
 }
}

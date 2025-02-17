package com.example.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.restapi.entity.User;
import com.example.restapi.repository.UserRepository;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

 @Autowired
 private UserRepository userRepository;

 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  User domainUser = userRepository.findByUsername(username)
    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

  return org.springframework.security.core.userdetails.User
    .withUsername(domainUser.getUsername())
    .password(domainUser.getPassword())
    .authorities(Collections.emptyList())
    .build();
 }
}
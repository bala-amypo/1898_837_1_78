package com.example.demo.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;               [cite_start]// [cite: 297]
    private String email;
    private String password;
    private String role;               // Optional
    private String availabilityStatus; // Optional
}
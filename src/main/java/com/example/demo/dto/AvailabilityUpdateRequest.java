package com.example.demo.dto;

import lombok.Data;

@Data
public class AvailabilityUpdateRequest {
    private Long volunteerId;          [cite_start]// [cite: 301]
    private String availabilityStatus; [cite_start]// [cite: 302]
}
package com.example.demo.dto;

import lombok.Data;

@Data
public class AvailabilityUpdateRequest {
    private Long volunteerId;
    private String availabilityStatus;
}
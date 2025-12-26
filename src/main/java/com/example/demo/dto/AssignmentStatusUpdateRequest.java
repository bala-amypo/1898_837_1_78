package com.example.demo.dto;

import lombok.Data;

@Data
public class AssignmentStatusUpdateRequest {
    private Long assignmentId;
    private String status;
}
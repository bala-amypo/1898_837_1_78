package com.example.demo.dto;

import lombok.Data;

@Data
public class AssignmentStatusUpdateRequest {
    private Long assignmentId; [cite_start]// [cite: 306]
    private String status;     [cite_start]// [cite: 307]
}
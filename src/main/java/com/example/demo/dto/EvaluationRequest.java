package com.example.demo.dto;

import lombok.Data;

@Data
public class EvaluationRequest {
    private Long assignmentId; [cite_start]// [cite: 311]
    private Integer rating;    [cite_start]// [cite: 312]
    private String comments;   [cite_start]// [cite: 313]
}
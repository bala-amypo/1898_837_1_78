package com.example.demo.dto;

import lombok.Data;

@Data
public class EvaluationRequest {
    private Long assignmentId;
    private Integer rating;
    private String comments;
}
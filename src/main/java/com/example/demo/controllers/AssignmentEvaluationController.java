package com.example.demo.controller;

import com.example.demo.dto.EvaluationRequest;
import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.service.AssignmentEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluations")
@RequiredArgsConstructor
public class AssignmentEvaluationController {

    private final AssignmentEvaluationService evaluationService;

    @PostMapping
    public AssignmentEvaluationRecord evaluate(@RequestBody EvaluationRequest request) {
        AssignmentEvaluationRecord record = new AssignmentEvaluationRecord();
        record.setAssignmentId(request.getAssignmentId());
        record.setRating(request.getRating());
        record.setComments(request.getComments());
        
        return evaluationService.evaluateAssignment(record);
    }
}
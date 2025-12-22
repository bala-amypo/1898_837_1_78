// src/main/java/com/example/demo/controller/AssignmentEvaluationController.java
package com.example.demo.controller;

import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.service.AssignmentEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluations")
public class AssignmentEvaluationController {

    private final AssignmentEvaluationService evaluationService;

    public AssignmentEvaluationController(AssignmentEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ResponseEntity<AssignmentEvaluationRecord> evaluate(@RequestBody AssignmentEvaluationRecord body) {
        return ResponseEntity.ok(evaluationService.evaluateAssignment(body));
    }
}

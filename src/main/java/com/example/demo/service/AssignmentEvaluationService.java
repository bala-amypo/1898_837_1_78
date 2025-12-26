package com.example.demo.service;

import com.example.demo.model.AssignmentEvaluationRecord;
import java.util.List;

public interface AssignmentEvaluationService {
    AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRecord evaluation); [cite_start]// [cite: 392]
    List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long assignmentId);
}
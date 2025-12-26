// src/main/java/com/example/demo/service/impl/AssignmentEvaluationServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.repository.AssignmentEvaluationRecordRepository;
import com.example.demo.repository.TaskAssignmentRecordRepository;
import com.example.demo.service.AssignmentEvaluationService;
import org.springframework.stereotype.Service;

@Service
public class AssignmentEvaluationServiceImpl implements AssignmentEvaluationService {

    private final AssignmentEvaluationRecordRepository evalRepo;
    private final TaskAssignmentRecordRepository assignmentRepo;

    public AssignmentEvaluationServiceImpl(AssignmentEvaluationRecordRepository evalRepo,
                                           TaskAssignmentRecordRepository assignmentRepo) {
        this.evalRepo = evalRepo;
        this.assignmentRepo = assignmentRepo;
    }

    @Override
    public AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRecord evaluation) {
        if (evaluation == null) throw new BadRequestException("Evaluation must not be null");
        TaskAssignmentRecord assignment = assignmentRepo.findById(evaluation.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));
        if (!"COMPLETED".equals(assignment.getStatus())) {
            throw new BadRequestException("Evaluation allowed only for COMPLETED assignments");
        }
        return evalRepo.save(evaluation);
    }
}

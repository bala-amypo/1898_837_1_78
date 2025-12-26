package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.repository.AssignmentEvaluationRecordRepository;
import com.example.demo.repository.TaskAssignmentRecordRepository;
import com.example.demo.service.AssignmentEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentEvaluationServiceImpl implements AssignmentEvaluationService {

    private final AssignmentEvaluationRecordRepository evaluationRepo;
    private final TaskAssignmentRecordRepository assignmentRepo;

    @Override
    public AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRecord evaluation) {
        TaskAssignmentRecord assignment = assignmentRepo.findById(evaluation.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));

        if (!"COMPLETED".equalsIgnoreCase(assignment.getStatus())) {
            throw new BadRequestException("Assignment must be COMPLETED to evaluate");
        }

        return evaluationRepo.save(evaluation);
    }

    @Override
    public List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long assignmentId) {
        return evaluationRepo.findByAssignmentId(assignmentId);
    }
}
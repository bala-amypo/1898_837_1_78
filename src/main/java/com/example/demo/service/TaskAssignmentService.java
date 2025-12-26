package com.example.demo.service;

import com.example.demo.model.TaskAssignmentRecord;
import java.util.List;

public interface TaskAssignmentService {
    TaskAssignmentRecord assignTask(Long taskId); [cite_start]// [cite: 376]
    List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId); [cite_start]// [cite: 377]
    List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long volunteerId); [cite_start]// [cite: 378]
    List<TaskAssignmentRecord> getAllAssignments();
    TaskAssignmentRecord updateAssignmentStatus(Long assignmentId, String status); // Support for DTO 3.5
}
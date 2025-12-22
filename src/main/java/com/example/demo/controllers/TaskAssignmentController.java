// src/main/java/com/example/demo/controller/TaskAssignmentController.java
package com.example.demo.controller;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.service.TaskAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class TaskAssignmentController {

    private final TaskAssignmentService assignmentService;

    public TaskAssignmentController(TaskAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/task/{taskId}/assign")
    public ResponseEntity<TaskAssignmentRecord> assign(@PathVariable Long taskId) {
        return ResponseEntity.ok(assignmentService.assignTask(taskId));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<TaskAssignmentRecord>> getByTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByTask(taskId));
    }

    @GetMapping("/volunteer/{volunteerId}")
    public ResponseEntity<List<TaskAssignmentRecord>> getByVolunteer(@PathVariable Long volunteerId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByVolunteer(volunteerId));
    }

    @PatchMapping("/{assignmentId}/status")
    public ResponseEntity<TaskAssignmentRecord> updateStatus(
            @PathVariable Long assignmentId,
            @RequestParam String status) {
        return ResponseEntity.ok(assignmentService.updateAssignmentStatus(assignmentId, status));
    }
}

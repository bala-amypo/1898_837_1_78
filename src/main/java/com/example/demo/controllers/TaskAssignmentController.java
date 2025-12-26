package com.example.demo.controller;

import com.example.demo.dto.AssignmentStatusUpdateRequest;
import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.service.TaskAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class TaskAssignmentController {

    private final TaskAssignmentService assignmentService;

    @GetMapping("/task/{taskId}")
    public List<TaskAssignmentRecord> getByTask(@PathVariable Long taskId) {
        return assignmentService.getAssignmentsByTask(taskId);
    }

    @GetMapping("/volunteer/{volunteerId}")
    public List<TaskAssignmentRecord> getByVolunteer(@PathVariable Long volunteerId) {
        return assignmentService.getAssignmentsByVolunteer(volunteerId);
    }

    @PatchMapping("/status")
    public TaskAssignmentRecord updateStatus(@RequestBody AssignmentStatusUpdateRequest request) {
        return assignmentService.updateAssignmentStatus(request.getAssignmentId(), request.getStatus());
    }
}
package com.example.demo.controller;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.model.TaskRecord;
import com.example.demo.service.TaskAssignmentService;
import com.example.demo.service.TaskRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRecordService taskService;
    private final TaskAssignmentService assignmentService;

    @PostMapping
    public TaskRecord createTask(@RequestBody TaskRecord task) {
        return taskService.createTask(task);
    }

    [cite_start]// [cite: 418] Endpoint calls assignTask(taskId)
    @PostMapping("/{taskId}/assign")
    public TaskAssignmentRecord assignTask(@PathVariable Long taskId) {
        return assignmentService.assignTask(taskId);
    }
}
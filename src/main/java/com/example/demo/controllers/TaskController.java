// src/main/java/com/example/demo/controller/TaskController.java
package com.example.demo.controller;

import com.example.demo.model.TaskRecord;
import com.example.demo.service.TaskRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRecordService taskService;

    public TaskController(TaskRecordService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskRecord> create(@RequestBody TaskRecord body) {
        return ResponseEntity.ok(taskService.createTask(body));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
}

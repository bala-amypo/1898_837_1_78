package com.example.demo.service;

import com.example.demo.model.TaskRecord;
import java.util.List;
import java.util.Optional;

public interface TaskRecordService {
    TaskRecord createTask(TaskRecord task); [cite_start]// [cite: 369]
    TaskRecord updateTask(Long id, TaskRecord updated);
    List<TaskRecord> getOpenTasks();
    Optional<TaskRecord> getTaskByCode(String code);
    List<TaskRecord> getAllTasks();
    TaskRecord getTaskById(Long id); [cite_start]// [cite: 370]
}
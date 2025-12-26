package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TaskRecord;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.service.TaskRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskRecordServiceImpl implements TaskRecordService {

    private final TaskRecordRepository repository;

    @Override
    public TaskRecord createTask(TaskRecord task) {
        // Status defaults to OPEN via @PrePersist or manual
        if(task.getStatus() == null) task.setStatus("OPEN");
        return repository.save(task);
    }

    @Override
    public TaskRecord updateTask(Long id, TaskRecord updated) {
        TaskRecord existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        
        if(updated.getTaskName() != null) existing.setTaskName(updated.getTaskName());
        if(updated.getRequiredSkillLevel() != null) existing.setRequiredSkillLevel(updated.getRequiredSkillLevel());
        if(updated.getPriority() != null) existing.setPriority(updated.getPriority());
        if(updated.getStatus() != null) existing.setStatus(updated.getStatus());
        
        return repository.save(existing);
    }

    @Override
    public List<TaskRecord> getOpenTasks() {
        return repository.findByStatus("OPEN");
    }

    @Override
    public Optional<TaskRecord> getTaskByCode(String code) {
        return repository.findByTaskCode(code);
    }
    
    @Override
    public List<TaskRecord> getAllTasks() {
        return repository.findAll();
    }
}
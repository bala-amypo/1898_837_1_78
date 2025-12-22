// src/main/java/com/example/demo/service/impl/TaskRecordServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TaskRecord;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.service.TaskRecordService;
import org.springframework.stereotype.Service;

@Service
public class TaskRecordServiceImpl implements TaskRecordService {

    private final TaskRecordRepository taskRepo;

    public TaskRecordServiceImpl(TaskRecordRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public TaskRecord createTask(TaskRecord task) {
        if (task == null) throw new BadRequestException("Task must not be null");
        if (task.getRequiredSkill() == null || task.getRequiredSkill().isBlank())
            throw new BadRequestException("Required skill must not be null");
        if (task.getRequiredSkillLevel() == null || task.getRequiredSkillLevel().isBlank())
            throw new BadRequestException("Required skill level must not be null");
        task.setStatus("OPEN");
        return taskRepo.save(task);
    }

    @Override
    public TaskRecord getTaskById(Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }
}

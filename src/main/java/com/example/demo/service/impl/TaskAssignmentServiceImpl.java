// src/main/java/com/example/demo/service/impl/TaskAssignmentServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.model.TaskRecord;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.repository.TaskAssignmentRecordRepository;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.repository.VolunteerProfileRepository;
import com.example.demo.repository.VolunteerSkillRecordRepository;
import com.example.demo.service.TaskAssignmentService;
import com.example.demo.util.SkillLevelUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskAssignmentServiceImpl implements TaskAssignmentService {

    private final TaskAssignmentRecordRepository assignmentRepo;
    private final TaskRecordRepository taskRepo;
    private final VolunteerProfileRepository profileRepo;
    private final VolunteerSkillRecordRepository skillRepo;

    public TaskAssignmentServiceImpl(TaskAssignmentRecordRepository assignmentRepo,
                                     TaskRecordRepository taskRepo,
                                     VolunteerProfileRepository profileRepo,
                                     VolunteerSkillRecordRepository skillRepo) {
        this.assignmentRepo = assignmentRepo;
        this.taskRepo = taskRepo;
        this.profileRepo = profileRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public TaskAssignmentRecord assignTask(Long taskId) {
        if (assignmentRepo.existsByTaskIdAndStatus(taskId, "ACTIVE")) {
            throw new BadRequestException("Task already has an ACTIVE assignment");
        }

        TaskRecord task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        List<VolunteerProfile> available = profileRepo.findByAvailabilityStatus("AVAILABLE");
        VolunteerProfile chosen = null;

        for (VolunteerProfile vp : available) {
            List<VolunteerSkillRecord> skills = skillRepo.findByVolunteerId(vp.getId());
            boolean matches = skills.stream().anyMatch(s ->
                    s.getSkillName().equalsIgnoreCase(task.getRequiredSkill()) &&
                    SkillLevelUtil.meetsOrExceeds(s.getSkillLevel(), task.getRequiredSkillLevel())
            );
            if (matches) {
                chosen = vp;
                break;
            }
        }

        if (chosen == null) {
            throw new BadRequestException("No volunteer meets the required skill level");
        }

        TaskAssignmentRecord assignment = new TaskAssignmentRecord(task.getId(), chosen.getId(), "ACTIVE");
        TaskAssignmentRecord saved = assignmentRepo.save(assignment);

        task.setStatus("ACTIVE");
        taskRepo.save(task);

        return saved;
    }

    @Override
    public List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId) {
        return assignmentRepo.findByTaskId(taskId);
    }

    @Override
    public List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long volunteerId) {
        return assignmentRepo.findByVolunteerId(volunteerId);
    }

    @Override
    public TaskAssignmentRecord updateAssignmentStatus(Long assignmentId, String status) {
        TaskAssignmentRecord assignment = assignmentRepo.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));
        assignment.setStatus(status);
        return assignmentRepo.save(assignment);
    }
}

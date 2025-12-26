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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskAssignmentServiceImpl implements TaskAssignmentService {

    private final TaskAssignmentRecordRepository assignmentRepo;
    private final TaskRecordRepository taskRepo;
    private final VolunteerProfileRepository volunteerRepo;
    private final VolunteerSkillRecordRepository skillRepo;

    @Override
    @Transactional
    public TaskAssignmentRecord assignTask(Long taskId) {
        // 1. Check for active assignment
        if (assignmentRepo.existsByTaskIdAndStatus(taskId, "ACTIVE")) {
            throw new BadRequestException("Task already has an ACTIVE assignment");
        }

        // 2. Fetch Task
        TaskRecord task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        // 3. Find Available Volunteers
        List<VolunteerProfile> candidates = volunteerRepo.findByAvailabilityStatus("AVAILABLE");
        if (candidates.isEmpty()) {
            throw new BadRequestException("No AVAILABLE volunteers found");
        }

        // 4. Match Logic
        VolunteerProfile selectedVolunteer = null;
        int requiredRank = SkillLevelUtil.levelRank(task.getRequiredSkillLevel());

        for (VolunteerProfile vol : candidates) {
            List<VolunteerSkillRecord> skills = skillRepo.findByVolunteerId(vol.getId());
            for (VolunteerSkillRecord skill : skills) {
                // Must match Name AND Level >= Required
                if (skill.getSkillName().equalsIgnoreCase(task.getRequiredSkill())) {
                    int volRank = SkillLevelUtil.levelRank(skill.getSkillLevel());
                    if (volRank >= requiredRank) {
                        selectedVolunteer = vol;
                        break;
                    }
                }
            }
            if (selectedVolunteer != null) break;
        }

        if (selectedVolunteer == null) {
            throw new BadRequestException("No volunteer found with required skill level");
        }

        // 5. Create Assignment
        TaskAssignmentRecord assignment = new TaskAssignmentRecord();
        assignment.setTaskId(taskId);
        assignment.setVolunteerId(selectedVolunteer.getId());
        assignment.setStatus("ACTIVE");
        
        task.setStatus("ACTIVE");
        taskRepo.save(task);

        return assignmentRepo.save(assignment);
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
    public List<TaskAssignmentRecord> getAllAssignments() {
        return assignmentRepo.findAll();
    }
}
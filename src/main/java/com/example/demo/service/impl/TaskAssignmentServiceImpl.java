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
        [cite_start]// [cite: 105, 341] Test expects message "ACTIVE assignment"
        if (assignmentRepo.existsByTaskIdAndStatus(taskId, "ACTIVE")) {
            throw new BadRequestException("Task already has an ACTIVE assignment");
        }

        TaskRecord task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        List<VolunteerProfile> availableVolunteers = volunteerRepo.findByAvailabilityStatus("AVAILABLE");
        if (availableVolunteers.isEmpty()) {
            throw new BadRequestException("No AVAILABLE volunteers found"); [cite_start]// [cite: 54]
        }

        VolunteerProfile selectedVolunteer = null;
        int requiredRank = SkillLevelUtil.levelRank(task.getRequiredSkillLevel());

        for (VolunteerProfile vol : availableVolunteers) {
            List<VolunteerSkillRecord> skills = skillRepo.findByVolunteerId(vol.getId());
            for (VolunteerSkillRecord skill : skills) {
                if (skill.getSkillName().equalsIgnoreCase(task.getRequiredSkill())) {
                    int volRank = SkillLevelUtil.levelRank(skill.getSkillLevel());
                    [cite_start]// [cite: 122] Logic to ensure skill level is sufficient
                    if (volRank >= requiredRank) {
                        selectedVolunteer = vol;
                        break;
                    }
                }
            }
            if (selectedVolunteer != null) break;
        }

        [cite_start]// [cite: 121] If no volunteer meets criteria (including level), throw exception
        if (selectedVolunteer == null) {
            throw new BadRequestException("No volunteer found with required skill level");
        }

        TaskAssignmentRecord assignment = new TaskAssignmentRecord();
        assignment.setTaskId(taskId);
        assignment.setVolunteerId(selectedVolunteer.getId());
        assignment.setStatus("ACTIVE"); [cite_start]// [cite: 76]

        // Update task status as per doc requirement
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
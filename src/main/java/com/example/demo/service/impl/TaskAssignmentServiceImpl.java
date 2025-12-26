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
        [cite_start]// [cite: 380] Check for active assignment. Message MUST contain "ACTIVE assignment"
        if (assignmentRepo.existsByTaskIdAndStatus(taskId, "ACTIVE")) {
            throw new BadRequestException("Task already has an ACTIVE assignment"); [cite_start]// [cite: 105]
        }

        [cite_start]// [cite: 382] Fetch task
        TaskRecord task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        [cite_start]// [cite: 383] Fetch AVAILABLE volunteers
        List<VolunteerProfile> availableVolunteers = volunteerRepo.findByAvailabilityStatus("AVAILABLE");
        if (availableVolunteers.isEmpty()) {
            throw new BadRequestException("No AVAILABLE volunteers found"); [cite_start]// [cite: 54]
        }

        VolunteerProfile selectedVolunteer = null;
        int requiredRank = SkillLevelUtil.levelRank(task.getRequiredSkillLevel());

        [cite_start]// [cite: 384] Match logic
        for (VolunteerProfile vol : availableVolunteers) {
            List<VolunteerSkillRecord> skills = skillRepo.findByVolunteerId(vol.getId());
            for (VolunteerSkillRecord skill : skills) {
                // Check name and level
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

        [cite_start]// [cite: 388] Throw if insufficient skill level
        if (selectedVolunteer == null) {
            throw new BadRequestException("No volunteer found with required skill level"); [cite_start]// [cite: 122]
        }

        [cite_start]// [cite: 386] Create assignment
        TaskAssignmentRecord assignment = new TaskAssignmentRecord();
        assignment.setTaskId(taskId);
        assignment.setVolunteerId(selectedVolunteer.getId());
        assignment.setStatus("ACTIVE"); 

        [cite_start]// [cite: 386] Update task status
        task.setStatus("ACTIVE");
        taskRepo.save(task);

        return assignmentRepo.save(assignment);
    }

    @Override
    public List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId) {
        return assignmentRepo.findByTaskId(taskId); [cite_start]// [cite: 331]
    }

    @Override
    public List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long volunteerId) {
        return assignmentRepo.findByVolunteerId(volunteerId); [cite_start]// [cite: 332]
    }

    @Override
    public List<TaskAssignmentRecord> getAllAssignments() {
        return assignmentRepo.findAll();
    }

    @Override
    public TaskAssignmentRecord updateAssignmentStatus(Long assignmentId, String status) {
        TaskAssignmentRecord assignment = assignmentRepo.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));
        
        assignment.setStatus(status);
        return assignmentRepo.save(assignment);
    }
}
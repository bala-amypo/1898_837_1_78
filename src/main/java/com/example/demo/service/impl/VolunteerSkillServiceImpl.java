package com.example.demo.service.impl;

import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.repository.VolunteerSkillRecordRepository;
import com.example.demo.service.VolunteerSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerSkillServiceImpl implements VolunteerSkillService {

    private final VolunteerSkillRecordRepository repository;

    @Override
    public List<VolunteerSkillRecord> getSkillsByVolunteer(Long volunteerId) {
        return repository.findByVolunteerId(volunteerId);
    }

    @Override
    public VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRecord skill) {
        // FIX: Explicitly set timestamp for Mockito tests
        if (skill.getUpdatedAt() == null) {
            skill.setUpdatedAt(LocalDateTime.now());
        }
        return repository.save(skill);
    }

    @Override
    public VolunteerSkillRecord addSkill(VolunteerSkillRecord skill) {
        if (skill.getUpdatedAt() == null) {
            skill.setUpdatedAt(LocalDateTime.now());
        }
        return repository.save(skill);
    }
}
// src/main/java/com/example/demo/service/impl/VolunteerSkillServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.repository.VolunteerSkillRecordRepository;
import com.example.demo.service.VolunteerSkillService;
import com.example.demo.util.SkillLevelUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerSkillServiceImpl implements VolunteerSkillService {

    private final VolunteerSkillRecordRepository skillRepo;

    public VolunteerSkillServiceImpl(VolunteerSkillRecordRepository skillRepo) {
        this.skillRepo = skillRepo;
    }

    @Override
    public List<VolunteerSkillRecord> getSkillsByVolunteer(Long volunteerId) {
        return skillRepo.findByVolunteerId(volunteerId);
    }

    @Override
    public VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRecord skillRecord) {
        if (skillRecord == null) throw new BadRequestException("Skill record must not be null");
        SkillLevelUtil.validateNonBlankSkillName(skillRecord.getSkillName());
        if (skillRecord.getSkillLevel() == null ||
            (!skillRecord.getSkillLevel().equals("BEGINNER") &&
             !skillRecord.getSkillLevel().equals("INTERMEDIATE") &&
             !skillRecord.getSkillLevel().equals("EXPERT"))) {
            throw new BadRequestException("Invalid skill level");
        }
        return skillRepo.save(skillRecord);
    }
}

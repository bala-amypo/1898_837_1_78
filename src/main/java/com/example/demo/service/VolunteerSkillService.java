package com.example.demo.service;

import com.example.demo.model.VolunteerSkillRecord;
import java.util.List;

public interface VolunteerSkillService {
    List<VolunteerSkillRecord> getSkillsByVolunteer(Long volunteerId); [cite_start]// [cite: 361]
    VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRecord skill);
}
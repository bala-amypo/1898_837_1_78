package com.example.demo.service;

import com.example.demo.model.VolunteerSkillRecord;
import java.util.List;

public interface VolunteerSkillService {
    VolunteerSkillRecord addSkill(VolunteerSkillRecord skill);
    List<VolunteerSkillRecord> getSkillsByVolunteer(Long volunteerId);
}
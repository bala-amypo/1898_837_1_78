package com.example.demo.service;

import com.example.demo.model.VolunteerSkillRecord;
import java.util.List;

public interface VolunteerSkillService {
    List<VolunteerSkillRecord> getSkillsByVolunteer(Long volunteerId);
    
    // Used by the Test Suite
    VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRecord skill);

    // Used by your Controller
    VolunteerSkillRecord addSkill(VolunteerSkillRecord skill);
}
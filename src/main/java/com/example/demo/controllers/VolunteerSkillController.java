package com.example.demo.controller;

import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.service.VolunteerSkillService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/skills")
public class VolunteerSkillController {

    private final VolunteerSkillService skillService;

    public VolunteerSkillController(VolunteerSkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public VolunteerSkillRecord addSkill(@RequestBody VolunteerSkillRecord skill) {
        return skillService.addSkill(skill);
    }

    @GetMapping("/volunteer/{volunteerId}")
    public List<VolunteerSkillRecord> getSkills(@PathVariable Long volunteerId) {
        return skillService.getSkillsByVolunteer(volunteerId);
    }
}
// src/main/java/com/example/demo/controller/VolunteerSkillController.java
package com.example.demo.controller;

import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.service.VolunteerSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class VolunteerSkillController {

    private final VolunteerSkillService skillService;

    public VolunteerSkillController(VolunteerSkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/volunteer/{volunteerId}")
    public ResponseEntity<List<VolunteerSkillRecord>> getByVolunteer(@PathVariable Long volunteerId) {
        return ResponseEntity.ok(skillService.getSkillsByVolunteer(volunteerId));
    }

    @PostMapping
    public ResponseEntity<VolunteerSkillRecord> addOrUpdate(@RequestBody VolunteerSkillRecord body) {
        return ResponseEntity.ok(skillService.addOrUpdateSkill(body));
    }
}

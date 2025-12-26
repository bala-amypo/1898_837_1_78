package com.example.demo.controller;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.service.VolunteerProfileService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/volunteers")
public class VolunteerProfileController {

    private final VolunteerProfileService volunteerService;

    public VolunteerProfileController(VolunteerProfileService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    public VolunteerProfile register(@RequestBody VolunteerProfile profile) {
        return volunteerService.registerVolunteer(profile);
    }

    @PatchMapping("/{id}/availability")
    public VolunteerProfile updateAvailability(
            @PathVariable Long id, 
            @RequestParam String status) {
        return volunteerService.updateAvailability(id, status);
    }
    
    @GetMapping("/available")
    public List<VolunteerProfile> getAvailable() {
        return volunteerService.getAvailableVolunteers();
    }
}
// src/main/java/com/example/demo/controller/VolunteerProfileController.java
package com.example.demo.controller;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.service.VolunteerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteers")
public class VolunteerProfileController {

    private final VolunteerProfileService profileService;

    public VolunteerProfileController(VolunteerProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<VolunteerProfile> register(@RequestBody VolunteerProfile body) {
        return ResponseEntity.ok(profileService.registerVolunteer(body));
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<VolunteerProfile> updateAvailability(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(profileService.updateAvailability(id, status));
    }

    @GetMapping("/available")
    public ResponseEntity<List<VolunteerProfile>> getAvailable() {
        return ResponseEntity.ok(profileService.getAvailableVolunteers());
    }
}

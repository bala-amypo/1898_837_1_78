package com.example.demo.controller;

import com.example.demo.dto.AvailabilityUpdateRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.service.VolunteerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteers")
@RequiredArgsConstructor
public class VolunteerProfileController {

    private final VolunteerProfileService volunteerService;

    @PostMapping
    public VolunteerProfile register(@RequestBody RegisterRequest request) {
        return volunteerService.registerVolunteer(request);
    }

    @PatchMapping("/{id}/availability")
    public VolunteerProfile updateAvailability(@PathVariable Long id, 
                                               @RequestBody AvailabilityUpdateRequest request) {
        return volunteerService.updateAvailability(id, request.getAvailabilityStatus());
    }
}
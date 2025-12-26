// src/main/java/com/example/demo/service/impl/VolunteerProfileServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.repository.VolunteerProfileRepository;
import com.example.demo.service.VolunteerProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VolunteerProfileServiceImpl implements VolunteerProfileService {

    private final VolunteerProfileRepository profileRepository;

    public VolunteerProfileServiceImpl(VolunteerProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public VolunteerProfile registerVolunteer(VolunteerProfile profile) {
        if (profile == null) throw new BadRequestException("Profile must not be null");
        if (profile.getEmail() == null || profile.getEmail().isBlank())
            throw new BadRequestException("Email is required");
        profileRepository.findByEmail(profile.getEmail()).ifPresent(p -> {
            throw new BadRequestException("Email already registered");
        });
        if (profile.getAvailabilityStatus() == null || profile.getAvailabilityStatus().isBlank()) {
            profile.setAvailabilityStatus("UNAVAILABLE");
        }
        return profileRepository.save(profile);
    }

    @Override
    public VolunteerProfile updateAvailability(Long volunteerId, String availabilityStatus) {
        VolunteerProfile profile = profileRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found"));
        if (!Objects.equals(availabilityStatus, "AVAILABLE") &&
            !Objects.equals(availabilityStatus, "UNAVAILABLE")) {
            throw new BadRequestException("Invalid availability status");
        }
        profile.setAvailabilityStatus(availabilityStatus);
        return profileRepository.save(profile);
    }

    @Override
    public List<VolunteerProfile> getAvailableVolunteers() {
        return profileRepository.findByAvailabilityStatus("AVAILABLE");
    }
}

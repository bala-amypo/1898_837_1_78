package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.repository.VolunteerProfileRepository;
import com.example.demo.service.VolunteerProfileService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VolunteerProfileServiceImpl implements VolunteerProfileService {

    private final VolunteerProfileRepository repository;

    public VolunteerProfileServiceImpl(VolunteerProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public VolunteerProfile registerVolunteer(VolunteerProfile profile) {
        if (repository.findByEmail(profile.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        profile.setAvailabilityStatus("AVAILABLE");
        return repository.save(profile);
    }

    @Override
    public VolunteerProfile updateAvailability(Long volunteerId, String status) {
        if (!status.equals("AVAILABLE") && !status.equals("UNAVAILABLE")) {
            throw new BadRequestException("Invalid availability status");
        }

        VolunteerProfile profile = repository.findById(volunteerId)
            .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found"));
        
        profile.setAvailabilityStatus(status);
        return repository.save(profile);
    }

    @Override
    public List<VolunteerProfile> getAvailableVolunteers() {
        return repository.findByAvailabilityStatus("AVAILABLE");
    }
}
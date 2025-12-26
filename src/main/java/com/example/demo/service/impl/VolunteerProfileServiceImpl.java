package com.example.demo.service.impl;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.repository.VolunteerProfileRepository;
import com.example.demo.service.VolunteerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VolunteerProfileServiceImpl implements VolunteerProfileService {

    private final VolunteerProfileRepository repository;

    @Override
    public VolunteerProfile createVolunteer(VolunteerProfile profile) {
        [cite_start]// [cite: 34] Tests expect "Email already exists" on duplicate
        if (repository.existsByEmail(profile.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        return repository.save(profile);
    }

    @Override // Helper for Controller DTO mapping
    public VolunteerProfile registerVolunteer(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        VolunteerProfile profile = new VolunteerProfile();
        profile.setFullName(request.getName());
        profile.setEmail(request.getEmail());
        profile.setAvailabilityStatus(request.getAvailabilityStatus() != null ? request.getAvailabilityStatus() : "AVAILABLE");
        // Set other fields from request as needed
        return repository.save(profile);
    }

    @Override
    public VolunteerProfile getVolunteerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found"));
    }

    @Override
    public List<VolunteerProfile> getAllVolunteers() {
        return repository.findAll();
    }

    @Override
    public Optional<VolunteerProfile> findByVolunteerId(String volunteerId) {
        return repository.findByVolunteerId(volunteerId);
    }

    @Override
    public VolunteerProfile updateAvailability(Long id, String status) {
        VolunteerProfile vp = getVolunteerById(id);
        if (!status.equals("AVAILABLE") && !status.equals("UNAVAILABLE")) {
            throw new BadRequestException("Invalid availability status"); 
        }
        vp.setAvailabilityStatus(status);
        return repository.save(vp);
    }

    @Override
    public List<VolunteerProfile> getAvailableVolunteers() {
        return repository.findByAvailabilityStatus("AVAILABLE");
    }
}
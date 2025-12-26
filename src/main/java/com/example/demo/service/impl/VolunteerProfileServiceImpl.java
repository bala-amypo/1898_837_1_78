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
        if (repository.existsByEmail(profile.getEmail())) {
            throw new BadRequestException("Email already exists"); [cite_start]// [cite: 33]
        }
        return repository.save(profile);
    }

    @Override
    public VolunteerProfile registerVolunteer(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists"); [cite_start]// [cite: 207]
        }
        VolunteerProfile profile = new VolunteerProfile();
        profile.setFullName(request.getName());
        profile.setEmail(request.getEmail());
        profile.setAvailabilityStatus(request.getAvailabilityStatus() != null ? request.getAvailabilityStatus() : "AVAILABLE");
        // In a real app, password handling happens in Controller/Security layer, saving profile here
        return repository.save(profile);
    }

    @Override
    public VolunteerProfile getVolunteerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found")); [cite_start]// [cite: 343]
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
            throw new BadRequestException("Invalid availability status"); [cite_start]// [cite: 208]
        }
        vp.setAvailabilityStatus(status);
        return repository.save(vp);
    }

    @Override
    public List<VolunteerProfile> getAvailableVolunteers() {
        return repository.findByAvailabilityStatus("AVAILABLE"); [cite_start]// [cite: 358]
    }
}
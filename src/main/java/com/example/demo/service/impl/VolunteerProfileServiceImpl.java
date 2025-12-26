package com.example.demo.service.impl;

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
            throw new BadRequestException("Email already exists");
        }
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
    
    // Additional methods required by interface
    @Override
    public VolunteerProfile updateAvailability(Long id, String status) {
        VolunteerProfile vp = getVolunteerById(id);
        vp.setAvailabilityStatus(status);
        return repository.save(vp);
    }
}
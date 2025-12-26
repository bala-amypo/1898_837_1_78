package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.VolunteerProfile;
import java.util.List;
import java.util.Optional;

public interface VolunteerProfileService {
    VolunteerProfile createVolunteer(VolunteerProfile profile);
    VolunteerProfile registerVolunteer(RegisterRequest request);
    VolunteerProfile getVolunteerById(Long id);
    List<VolunteerProfile> getAllVolunteers();
    Optional<VolunteerProfile> findByVolunteerId(String volunteerId);
    VolunteerProfile updateAvailability(Long volunteerId, String availabilityStatus);
    List<VolunteerProfile> getAvailableVolunteers();
}
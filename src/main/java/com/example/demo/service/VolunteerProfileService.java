package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.VolunteerProfile;
import java.util.List;
import java.util.Optional;

public interface VolunteerProfileService {
    VolunteerProfile createVolunteer(VolunteerProfile profile);
    VolunteerProfile registerVolunteer(RegisterRequest request); [cite_start]// [cite: 353]
    VolunteerProfile getVolunteerById(Long id);
    List<VolunteerProfile> getAllVolunteers();
    Optional<VolunteerProfile> findByVolunteerId(String volunteerId);
    VolunteerProfile updateAvailability(Long volunteerId, String availabilityStatus); [cite_start]// [cite: 354]
    List<VolunteerProfile> getAvailableVolunteers(); [cite_start]// [cite: 355]
}
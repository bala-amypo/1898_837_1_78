// src/main/java/com/example/demo/service/VolunteerProfileService.java
package com.example.demo.service;

import com.example.demo.model.VolunteerProfile;

import java.util.List;

public interface VolunteerProfileService {
    VolunteerProfile registerVolunteer(VolunteerProfile profile);
    VolunteerProfile updateAvailability(Long volunteerId, String availabilityStatus);
    List<VolunteerProfile> getAvailableVolunteers();
}

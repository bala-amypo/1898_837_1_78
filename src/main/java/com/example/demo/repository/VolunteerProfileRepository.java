package com.example.demo.repository;

import com.example.demo.model.VolunteerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VolunteerProfileRepository extends JpaRepository<VolunteerProfile, Long> {
    // Required custom method to find available volunteers [cite: 150]
    List<VolunteerProfile> findByAvailabilityStatus(String status);
}
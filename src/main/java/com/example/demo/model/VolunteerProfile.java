package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "volunteer_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Business key used in tests (V001, etc)
    @Column(unique = true)
    private String volunteerId;

    private String fullName;
    private String email;
    private String phone;
    private String availabilityStatus; // AVAILABLE, UNAVAILABLE
}
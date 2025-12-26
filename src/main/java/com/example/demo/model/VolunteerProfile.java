package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "volunteer_profiles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite: 26]

    private String name; [cite: 27]

    @Column(unique = true)
    private String email; [cite: 28, 37]

    private String availabilityStatus; // "AVAILABLE" or "UNAVAILABLE" [cite: 29]

    @OneToMany(mappedBy = "volunteerId", cascade = CascadeType.ALL)
    private List<VolunteerSkillRecord> skills; [cite: 34]

    // Parameterized constructor as required [cite: 32]
    public VolunteerProfile(String name, String email, String availabilityStatus) {
        this.name = name;
        this.email = email;
        this.availabilityStatus = availabilityStatus;
    }
}
// src/main/java/com/example/demo/model/VolunteerProfile.java
package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "volunteer_profiles", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class VolunteerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String availabilityStatus; // "AVAILABLE" or "UNAVAILABLE"

    public VolunteerProfile() {}

    public VolunteerProfile(String name, String email, String availabilityStatus) {
        this.name = name;
        this.email = email;
        this.availabilityStatus = availabilityStatus;
    }

    // Getters and setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getAvailabilityStatus() { return availabilityStatus; }

    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VolunteerProfile)) return false;
        VolunteerProfile that = (VolunteerProfile) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}

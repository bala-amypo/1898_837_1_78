package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_assignment_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskAssignmentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;
    private Long volunteerId;
    
    // FIX: Initialize default here so it passes Mockito tests that ignore @PrePersist
    private String status = "ACTIVE"; 

    @PrePersist
    public void setDefaultStatus() {
        if (this.status == null) {
            this.status = "ACTIVE";
        }
    }
}
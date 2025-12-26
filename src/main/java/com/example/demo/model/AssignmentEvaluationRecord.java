package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignment_evaluation_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentEvaluationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long assignmentId;
    private Integer rating;
    private String comments;
    private String feedback;

    // FIX: Initialize default here so it passes Mockito tests that ignore @PrePersist
    private LocalDateTime evaluatedAt = LocalDateTime.now();

    @PrePersist
    public void setTimestamp() {
        if (this.evaluatedAt == null) {
            this.evaluatedAt = LocalDateTime.now();
        }
    }
}
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
    private String feedback; // From JPA test case source 87

    private LocalDateTime evaluatedAt;

    @PrePersist
    public void setTimestamp() {
        this.evaluatedAt = LocalDateTime.now();
    }
}
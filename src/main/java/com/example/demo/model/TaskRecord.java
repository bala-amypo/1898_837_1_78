package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskCode;
    private String taskName;
    private String description;
    private String requiredSkill;
    private String requiredSkillLevel;
    private String priority;
    private String status; // OPEN, ACTIVE, COMPLETED

    @PrePersist
    public void setDefaultStatus() {
        if (this.status == null) {
            this.status = "OPEN";
        }
    }
}
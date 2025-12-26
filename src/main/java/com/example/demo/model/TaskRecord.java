package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_records")
public class TaskRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String requiredSkill;

    private String requiredLevel;

    private String status; // Default: "OPEN"

    public TaskRecord() {}

    public TaskRecord(String title, String description, String requiredSkill, String requiredLevel) {
        this.title = title;
        this.description = description;
        this.requiredSkill = requiredSkill;
        this.requiredLevel = requiredLevel;
        this.status = "OPEN";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRequiredSkill() { return requiredSkill; }
    public void setRequiredSkill(String requiredSkill) { this.requiredSkill = requiredSkill; }
    public String getRequiredLevel() { return requiredLevel; }
    public void setRequiredLevel(String requiredLevel) { this.requiredLevel = requiredLevel; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
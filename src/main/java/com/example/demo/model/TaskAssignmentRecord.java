// src/main/java/com/example/demo/model/TaskAssignmentRecord.java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_assignment_records")
public class TaskAssignmentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long taskId;

    @Column(nullable = false)
    private Long volunteerId;

    @Column(nullable = false)
    private String status; // "ACTIVE", "COMPLETED", etc.

    public TaskAssignmentRecord() {}

    public TaskAssignmentRecord(Long taskId, Long volunteerId, String status) {
        this.taskId = taskId;
        this.volunteerId = volunteerId;
        this.status = status;
    }

    // Getters and setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getTaskId() { return taskId; }

    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public Long getVolunteerId() { return volunteerId; }

    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}

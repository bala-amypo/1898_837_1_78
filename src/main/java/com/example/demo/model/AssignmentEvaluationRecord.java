// src/main/java/com/example/demo/model/AssignmentEvaluationRecord.java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assignment_evaluation_records")
public class AssignmentEvaluationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long assignmentId;

    @Column(nullable = false)
    private Integer rating; // 1–5

    @Column(length = 2000)
    private String comments;

    public AssignmentEvaluationRecord() {}

    public AssignmentEvaluationRecord(Long assignmentId, Integer rating, String comments) {
        this.assignmentId = assignmentId;
        this.rating = rating;
        this.comments = comments;
    }

    // Getters and setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getAssignmentId() { return assignmentId; }

    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public Integer getRating() { return rating; }

    public void setRating(Integer rating) { this.rating = rating; }

    public String getComments() { return comments; }

    public void setComments(String comments) { this.comments = comments; }
}

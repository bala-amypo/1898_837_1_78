package com.example.demo.repository;

import com.example.demo.model.TaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRecordRepository extends JpaRepository<TaskRecord, Long> {
    List<TaskRecord> findByStatus(String status);
}
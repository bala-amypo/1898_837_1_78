// src/main/java/com/example/demo/repository/TaskRecordRepository.java
package com.example.demo.repository;

import com.example.demo.model.TaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRecordRepository extends JpaRepository<TaskRecord, Long> {
}

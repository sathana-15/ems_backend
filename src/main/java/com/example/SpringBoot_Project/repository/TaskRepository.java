package com.example.SpringBoot_Project.repository;

import com.example.SpringBoot_Project.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByAssignedEmployeeEmpId(int empId);
}

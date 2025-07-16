package com.example.SpringBoot_Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SpringBoot_Project.model.Task;
public interface TaskRepository extends JpaRepository<Task,Integer> {

}

package com.example.SpringBoot_Project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SpringBoot_Project.model.RegisterDetails;
import com.example.SpringBoot_Project.model.Task;
import com.example.SpringBoot_Project.repository.RegisterDetailsRepository;
import com.example.SpringBoot_Project.repository.TaskRepository;


@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    public String assignTaskById(int empId, Task task) {
        RegisterDetails employee = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        task.setAssignedEmployee(employee);
        taskRepository.save(task);
        return "Task assigned successfully to employee ID " + empId;
    }

}

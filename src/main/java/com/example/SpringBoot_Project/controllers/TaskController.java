package com.example.SpringBoot_Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.SpringBoot_Project.model.Task;
import com.example.SpringBoot_Project.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService t;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/id/{empId}")
    public String assignTaskById(@PathVariable int empId, @RequestBody Task task) {
        return t.assignTaskById(empId, task);
    }
}

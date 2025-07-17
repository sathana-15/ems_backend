package com.example.SpringBoot_Project.controllers;


import com.example.SpringBoot_Project.model.Task;
import com.example.SpringBoot_Project.model.UserDetailsDto;
import com.example.SpringBoot_Project.service.EmployeeService;
import com.example.SpringBoot_Project.model.RegisterDetails;
import com.example.SpringBoot_Project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaskService taskService;



    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/")
    public String route(){
        return "Welcome to Springboot Security";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee")
    public List<RegisterDetails> getMethod(){
        return employeeService.getMethod();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee/id/{empId}")
    public RegisterDetails getEmployeeById(@PathVariable int empId){
        return employeeService.getEmployeeById(empId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee/role/{role}")
    public RegisterDetails getEmployeeByRole(@PathVariable String role){
        return employeeService.getEmployeeByRole(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employee")
    public String postMethod(@RequestBody UserDetailsDto employee){
        return employeeService.addEmployee(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/employee/{empId}")
    public String putMethod(@PathVariable int empId,@RequestBody UserDetailsDto details){
        return employeeService.updateEmployee(empId,details);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employee/{empId}")
    public String deleteMethod(@PathVariable int empId){
        return employeeService.deleteEmployee(empId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee/{empId}/tasks")
    public List<Task> getTasksByEmployee(@PathVariable int empId) {
        return employeeService.getTasksByEmployeeId(empId);
    }







}

package com.example.SpringBoot_Project.service;

import com.example.SpringBoot_Project.model.RegisterDetails;
import com.example.SpringBoot_Project.model.Roles;
import com.example.SpringBoot_Project.model.Task;
import com.example.SpringBoot_Project.model.UserDetailsDto;
import com.example.SpringBoot_Project.repository.RegisterDetailsRepository;
import com.example.SpringBoot_Project.repository.RolesRepository;
import com.example.SpringBoot_Project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;
@Service
public class EmployeeService {
    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolesRepository rolesRepository;

    public List<RegisterDetails> getMethod() {
        return registerDetailsRepository.findAll();
    }

    public RegisterDetails getEmployeeById(int empId) {
        return registerDetailsRepository.findById(empId).orElse(new RegisterDetails());
    }

    public RegisterDetails getEmployeeByRole(String role) {
        return registerDetailsRepository.findByRole(role).orElse(new RegisterDetails());
    }

    public String addEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());
        Set<Roles> roles = new HashSet<>();
        for(String roleName: register.getRoleNames()){
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(()->new RuntimeException("User not found" + roleName));
            roles.add(role);
        }
        registerDetails.setRoles(roles);
        System.out.println("Registration"+ registerDetails);
        registerDetailsRepository.save(registerDetails);
        return "Employee Added Successfully";
    }

    public String updateEmployee(int empId, UserDetailsDto details) {
        RegisterDetails user = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("No such user present"));

        user.setName(details.getName());
        user.setEmail(details.getEmail());
        user.setPassword(passwordEncoder.encode(details.getPassword()));
        user.setUserName(details.getUserName());


        Set<Roles> roles = new HashSet<>();
        for (String roleName : details.getRoleNames()) {
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }
        user.setRoles(roles);

        registerDetailsRepository.save(user);
        return "Employee updated successfully";
    }


    public String deleteEmployee(int empId) {
        Optional<RegisterDetails> optionalUser = registerDetailsRepository.findById(empId);

        if (optionalUser.isEmpty()) {
            return "Employee not found";
        }

        RegisterDetails user = optionalUser.get();


        user.getRoles().clear();
        registerDetailsRepository.save(user);


        registerDetailsRepository.deleteById(empId);

        return "Employee deleted successfully";
    }


    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksByEmployeeId(int empId) {
        return taskRepository.findByAssignedEmployeeEmpId(empId);
    }


}

package com.example.SpringBoot_Project.service;

import com.example.SpringBoot_Project.model.RegisterDetails;
import com.example.SpringBoot_Project.model.Roles;
import com.example.SpringBoot_Project.model.UserDetailsDto;
import com.example.SpringBoot_Project.repository.RegisterDetailsRepository;
import com.example.SpringBoot_Project.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        RegisterDetails user=registerDetailsRepository.findById(empId)
                .orElseThrow(()->new RuntimeException("No such user present"));
        user.setName(details.getName());
        user.setEmail(details.getEmail());
        user.setPassword(details.getPassword());
        user.setUserName(details.getUserName());
        registerDetailsRepository.save(user);
        return "Employee updated successfully";
    }

    public String deleteEmployee(int empId) {
        registerDetailsRepository.deleteById(empId);
        return "Employee deleted successfully";
    }
}

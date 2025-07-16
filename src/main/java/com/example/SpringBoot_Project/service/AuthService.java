package com.example.SpringBoot_Project.service;

import com.example.SpringBoot_Project.jwt.JwtTokenProvider;
import com.example.SpringBoot_Project.model.AuthResponse;
import com.example.SpringBoot_Project.model.RegisterDetails;
import com.example.SpringBoot_Project.model.Roles;
import com.example.SpringBoot_Project.model.UserDetailsDto;
import com.example.SpringBoot_Project.repository.RegisterDetailsRepository;
import com.example.SpringBoot_Project.repository.RegisterRepository;
import com.example.SpringBoot_Project.repository.RolesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails=new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());
        Set<Roles> roles = new HashSet<>();
        for(String roleName : register.getRoleNames()){
            Roles role=rolesRepository.findByRoleName(roleName)
                    .orElseThrow(()->new RuntimeException("Role not found" + roleName));
            roles.add(role);
        }
        registerDetails.setRoles(roles);
        System.out.println("Registration" + registerDetails);
        registerDetailsRepository.save(registerDetails);
        return "Employee Registered Successfully";
    }

/*public String authenticate(RegisterDetails login) {
       RegisterDetails user=registerDetailsRepository.findByEmail(login.getEmail());
       if(user!=null){
           if(passwordEncoder.matches(login.getPassword(),user.getPassword())){
               return "Login Successful";
         }
       }
          return "Login not successful";
    }
*/
public AuthResponse authenticate(RegisterDetails login) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    login.getUserName(), login.getPassword()));

    String token = jwtTokenProvider.generateToken(authentication);
    RegisterDetails user = registerRepository.findByUserName(login.getUserName())
            .orElseThrow(() -> new RuntimeException("User not found"));
    Set<String> roleNames = user.getRoles()
            .stream()
            .map(role -> role.getRoleName())
            .collect(Collectors.toSet());

    return new AuthResponse(token, user.getUserName(), roleNames);
}

    public Optional<RegisterDetails> getUserByUsername(String username){
        return registerRepository.findByUserName(username);
    }
}

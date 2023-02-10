package com.example.touristAttractions.services;

import com.example.touristAttractions.model.Role;
import com.example.touristAttractions.model.User;
import com.example.touristAttractions.repositories.UserRepository;
import com.example.touristAttractions.security.AuthenticationRequest;
import com.example.touristAttractions.security.AuthenticationResponse;
import com.example.touristAttractions.security.JwtUtils;
import com.example.touristAttractions.security.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse registerUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        var jwtToken = jwtUtils.generateJwtToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }
        catch (Exception e){
            throw e;
        }
        UserDetails userDetails = userRepository.findByUsername(request.getUsername());
        var jwtToken = jwtUtils.generateJwtToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}

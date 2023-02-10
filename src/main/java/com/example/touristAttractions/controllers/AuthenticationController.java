package com.example.touristAttractions.controllers;

import com.example.touristAttractions.security.AuthenticationRequest;
import com.example.touristAttractions.security.AuthenticationResponse;
import com.example.touristAttractions.security.RegisterRequest;
import com.example.touristAttractions.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request){
        return new ResponseEntity<>(authenticationService.registerUser(request), HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(authenticationService.loginUser(request), HttpStatus.OK);
    }

}

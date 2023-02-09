package com.example.touristAttractions.controllers;

import com.example.touristAttractions.model.User;
import com.example.touristAttractions.services.UserDetailsServiceCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    UserDetailsServiceCustom userDetailsServiceCustom;

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return new ResponseEntity<>(userDetailsServiceCustom.registerUser(user), HttpStatus.OK);
    }
}

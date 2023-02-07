package com.example.touristAttractions.controllers;


import com.example.touristAttractions.services.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/attractions")
public class AttractionController {
    @Autowired
    private AttractionService attractionService;
}

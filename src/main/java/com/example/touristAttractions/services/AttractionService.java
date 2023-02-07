package com.example.touristAttractions.services;

import com.example.touristAttractions.repositories.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// all relevant data processing
@Service
public class AttractionService {
    @Autowired
    private AttractionRepository attractionRepository;
}

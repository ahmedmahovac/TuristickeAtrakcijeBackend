package com.example.touristAttractions.controllers;


import com.example.touristAttractions.domain.Attraction;
import com.example.touristAttractions.domain.Popularity;
import com.example.touristAttractions.services.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path = "/api/attractions")
public class AttractionController {
    @Autowired
    private AttractionService attractionService;

    @GetMapping(path = "/search", produces = "application/json")
    private ResponseEntity<List<Attraction>> getActiveAttractions(@RequestParam(required = false, name = "popularity") Popularity popularity, @RequestParam(required = false, name = "name") String name){
        return new ResponseEntity<>(attractionService.getActiveAttractions(popularity, name), HttpStatus.OK);
    }

    @PutMapping(path = "/rate/{id}", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Attraction> rateAttraction(@PathVariable Integer id, @RequestBody Map<String, ?> input){
        var rating = input.get("rating");
        if(rating instanceof Double){
           return new ResponseEntity<>(attractionService.rateAttraction(id, (Double) rating), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

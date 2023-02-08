package com.example.touristAttractions.controllers;


import com.example.touristAttractions.model.Attraction;
import com.example.touristAttractions.model.Picture;
import com.example.touristAttractions.model.Popularity;
import com.example.touristAttractions.services.AttractionService;
import com.example.touristAttractions.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path = "/api/attractions")
public class AttractionController {
    @Autowired
    private AttractionService attractionService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping(path = "/search", produces = "application/json")
    public ResponseEntity<List<Attraction>> getActiveAttractions(@RequestParam(required = false, name = "popularity") Popularity popularity, @RequestParam(required = false, name = "name") String name){
        // implement better solution for this, maybe use string only instead of enum
        return new ResponseEntity<>(attractionService.getActiveAttractions(popularity, name), HttpStatus.OK);
    }

    @PutMapping(path = "/rate/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Attraction> rateAttraction(@PathVariable Integer id, @RequestBody Map<String, ?> input){
        var rating = input.get("rating");
        // implement type check
        if(Integer.parseInt(rating.toString())<1 || Integer.parseInt(rating.toString())>5){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Attraction updatedAttraction = attractionService.rateAttraction(id, Integer.parseInt(rating.toString()));
        if(updatedAttraction==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedAttraction, HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/picture")
    public ResponseEntity<Picture> addPicture(@PathVariable Integer id, @RequestParam("picture") MultipartFile picture){
        return new ResponseEntity<>(fileStorageService.storeFile(id, picture), HttpStatus.OK);
    }

    // maybe endpoint for multiple files upload?


    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void deleteAttraction(@PathVariable Integer id){
        attractionService.deleteAttraction(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Attraction> updateAttraction(@PathVariable Integer id, @RequestBody Attraction attraction){
        return new ResponseEntity<>(attractionService.updateAttraction(id, attraction), HttpStatus.OK);
    }

}

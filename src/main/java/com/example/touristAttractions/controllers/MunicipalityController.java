package com.example.touristAttractions.controllers;

import com.example.touristAttractions.model.Attraction;
import com.example.touristAttractions.model.Municipality;
import com.example.touristAttractions.services.MunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/municipalities")
public class MunicipalityController {
    @Autowired
    private MunicipalityService municipalityService;


    @DeleteMapping(path = "/{id}")
    public void deleteCountry(@PathVariable Integer id){
        municipalityService.deleteMunicipality(id);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<Municipality> getMunicipality(@PathVariable Integer id){
        return new ResponseEntity<>(municipalityService.getMunicipality(id), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<List<Municipality>> getAllMunicipalities(){
        return new ResponseEntity<>(municipalityService.getAllMunicipalities(), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/attractions", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<Attraction> addAttraction(@PathVariable Integer id, @RequestBody Attraction attraction){
        return new ResponseEntity<>(municipalityService.addAttraction(id, attraction), HttpStatus.OK);
    }
}

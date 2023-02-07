package com.example.touristAttractions.controllers;

import com.example.touristAttractions.domain.Country;
import com.example.touristAttractions.domain.Municipality;
import com.example.touristAttractions.services.CountryService;
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


    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<Municipality> addCountry(@RequestBody Municipality municipality){
        return new ResponseEntity<>(municipalityService.addMunicipality(municipality), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCountry(@RequestParam Integer id){
        municipalityService.deleteMunicipality(id);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<Municipality> getMunicipality(@RequestParam Integer id){
        return new ResponseEntity<>(municipalityService.getMunicipality(id), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<List<Municipality>> getAllMunicipalities(){
        return new ResponseEntity<>(municipalityService.getAllMunicipalities(), HttpStatus.OK);
    }
}

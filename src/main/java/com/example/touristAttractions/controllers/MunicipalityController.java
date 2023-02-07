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
}

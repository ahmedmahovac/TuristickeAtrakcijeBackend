package com.example.touristAttractions.controllers;

import com.example.touristAttractions.model.Country;
import com.example.touristAttractions.model.Municipality;
import com.example.touristAttractions.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*   @PostMapping(path="/posts", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<Post> addNewPost (@RequestBody Post post){*/

@Controller
@RequestMapping(path = "/api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;


    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<Country> addCountry(@RequestBody Country country){
        return new ResponseEntity<>(countryService.addCountry(country), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCountry(@PathVariable Integer id){
        countryService.deleteCountry(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<Country> getCountry(@PathVariable Integer id){
        return new ResponseEntity<>(countryService.getCountry(id), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<List<Country>> getAllCountries(){
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/municipalities", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<Municipality> addMunicipality(@PathVariable Integer id,@RequestBody Municipality municipality){
        return new ResponseEntity<>(countryService.addMunicipality(id, municipality), HttpStatus.OK);
    }

    @GetMapping(path="/{countryId}/municipalities")
    public @ResponseBody ResponseEntity<List<Municipality>> getMunicipalitiesForCountry(@PathVariable Integer countryId){
        return new ResponseEntity<>(countryService.getMunicipalities(countryId), HttpStatus.OK);
    }
}

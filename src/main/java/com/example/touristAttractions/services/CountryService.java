package com.example.touristAttractions.services;

import com.example.touristAttractions.domain.Country;
import com.example.touristAttractions.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public Country addCountry(Country country) {
        return countryRepository.save(country); // this waits for code to execute
    }

    public void deleteCountry(Integer id) {
        countryRepository.deleteById(id);
    }

    public Country getCountry(Integer id) {
        return countryRepository.findById(id).get();
    }

    public List<Country> getAllCountries(){
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().forEach(countries::add);
        return countries;
    }
}

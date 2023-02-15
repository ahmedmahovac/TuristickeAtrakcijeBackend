package com.example.touristAttractions.services;

import com.example.touristAttractions.model.Attraction;
import com.example.touristAttractions.model.Municipality;
import com.example.touristAttractions.repositories.AttractionRepository;
import com.example.touristAttractions.repositories.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MunicipalityService {
    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    public void deleteMunicipality(Integer id) {
        municipalityRepository.deleteById(id);
    }

    public Municipality getMunicipality(Integer id) {
        return municipalityRepository.findById(id).get();
    }


    public List<Municipality> getAllMunicipalities() {
        List<Municipality> municipalities = new ArrayList<>();
        municipalityRepository.findAll().forEach(municipalities::add);
        return municipalities;
    }

    public Attraction addAttraction(Integer municipalityId, Attraction attraction) {
        if(!municipalityRepository.existsById(municipalityId)){
            return null;
        }
        Municipality municipality = municipalityRepository.findById(municipalityId).get();
        attraction.setMunicipality(municipality);
        return attractionRepository.save(attraction);
    }

    public List<Attraction> getAttractionsByMunicipalityId(Integer municipalityId) {
        return attractionRepository.findByMunicipalityId(municipalityId);
    }
}

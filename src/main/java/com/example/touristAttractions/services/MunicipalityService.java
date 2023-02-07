package com.example.touristAttractions.services;

import com.example.touristAttractions.domain.Municipality;
import com.example.touristAttractions.repositories.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MunicipalityService {
    @Autowired
    private MunicipalityRepository municipalityRepository;

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
}

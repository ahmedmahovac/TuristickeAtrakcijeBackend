package com.example.touristAttractions.repositories;

import com.example.touristAttractions.model.Attraction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called attractionRepository
public interface AttractionRepository extends CrudRepository<Attraction, Integer>{
    List<Attraction> findByMunicipalityId(Integer municipalityId);
}

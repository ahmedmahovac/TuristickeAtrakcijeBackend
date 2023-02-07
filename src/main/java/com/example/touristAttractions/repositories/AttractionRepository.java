package com.example.touristAttractions.repositories;

import com.example.touristAttractions.domain.Attraction;
import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called attractionRepository
public interface AttractionRepository extends CrudRepository<Attraction, Integer>{
}

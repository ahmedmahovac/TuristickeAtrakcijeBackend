package com.example.touristAttractions.repositories;

import com.example.touristAttractions.domain.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {
}

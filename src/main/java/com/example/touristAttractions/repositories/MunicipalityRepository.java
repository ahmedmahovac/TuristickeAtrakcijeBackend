package com.example.touristAttractions.repositories;

import com.example.touristAttractions.model.Municipality;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MunicipalityRepository extends CrudRepository<Municipality, Integer> {
    List<Municipality> findByCountryId(Integer id);
}

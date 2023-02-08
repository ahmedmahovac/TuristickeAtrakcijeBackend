package com.example.touristAttractions.repositories;

import com.example.touristAttractions.model.Picture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PictureRepository extends CrudRepository<Picture, Integer> {
    public List<Picture> findByAttractionId(Integer id);
}

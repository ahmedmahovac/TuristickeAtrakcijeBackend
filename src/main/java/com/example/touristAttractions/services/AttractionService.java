package com.example.touristAttractions.services;

import com.example.touristAttractions.domain.Popularity;
import com.example.touristAttractions.domain.Attraction;
import com.example.touristAttractions.repositories.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// all relevant data processing
@Service
public class AttractionService {
    @Autowired
    private AttractionRepository attractionRepository;

    public List<Attraction> getActiveAttractions(Popularity popularity, String name) {
        List<Attraction> allAttractions = new ArrayList<>();
         attractionRepository.findAll().forEach(allAttractions::add);
         if(popularity!=null){
             System.out.println("popularity nije null");
             // if it's VERYPOPULAR or EXTREMELYPOPULAR, it's automatically POPULAR
             Double minimum = 2.5;
             if(popularity==Popularity.VERYPOPULAR){
                 minimum = 3.5;
             } else if (popularity==Popularity.EXTREMELYPOPULAR) {
                 minimum = 4.5;
             }
             Double minimumFinal = minimum; // workaround
             allAttractions.stream().filter((attraction)->{
                 return (attraction.getActive() && attraction.getRatingSum()>minimumFinal);
             }).collect(Collectors.toList());
         }
         if(name!=null){
             allAttractions.stream().filter((attraction)->{
                 return attraction.getName().toLowerCase().contains(name.toLowerCase());
             }).collect(Collectors.toList());
         }
         return allAttractions;
    }


    public Attraction rateAttraction(Integer id, Double rating) {
        if(!attractionRepository.existsById(id)){
            return null;
        }
        Attraction attraction = attractionRepository.findById(id).get();
        attraction.setRatingSum(attraction.getRatingSum()+rating); // setting new rating
        attraction.setRatingsCount(attraction.getRatingsCount()+1);
        return attractionRepository.save(attraction);
    }
}

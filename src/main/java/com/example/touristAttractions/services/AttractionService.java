package com.example.touristAttractions.services;

import com.example.touristAttractions.model.Picture;
import com.example.touristAttractions.model.Popularity;
import com.example.touristAttractions.model.Attraction;
import com.example.touristAttractions.repositories.AttractionRepository;
import com.example.touristAttractions.repositories.PictureRepository;
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

    @Autowired
    private PictureRepository pictureRepository;

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
             allAttractions = allAttractions.stream().filter((attraction)->{
                 return (attraction.getActive() && attraction.getRatingAvg()>=minimumFinal);
             }).collect(Collectors.toList());
         }
         if(name!=null){
            allAttractions = allAttractions.stream().filter((attraction)->{
                 return  (attraction.getActive() && attraction.getName().toLowerCase().contains(name.toLowerCase()));
             }).collect(Collectors.toList());
         }
         return allAttractions;
    }


    public Attraction rateAttraction(Integer id, Integer rating) {
        if(!attractionRepository.existsById(id)){
            System.out.println(id);
            return null;
        }
        Attraction attraction = attractionRepository.findById(id).get();
        attraction.setRatingSum(attraction.getRatingSum()+rating); // setting new rating
        attraction.setRatingsCount(attraction.getRatingsCount()+1);
        return attractionRepository.save(attraction);
    }


    public void deleteAttraction(Integer id) {
        attractionRepository.deleteById(id);
    }

    public Attraction updateAttraction(Integer id, Attraction attraction) {
        if(!attractionRepository.existsById(id)) return null;
        Attraction existingAttraction = attractionRepository.findById(id).get();
        if(attraction.getActive()!=null){
            existingAttraction.setActive(attraction.getActive());
        }
        if(attraction.getName()!=null){
            existingAttraction.setName(attraction.getName());
        }
        if(attraction.getDescription()!=null){
            existingAttraction.setDescription(attraction.getDescription());
        }
        if(attraction.getLat()!=null){
            existingAttraction.setLat(attraction.getLat());
        }
        if(attraction.getLon()!=null){
            existingAttraction.setLon(attraction.getLon());
        }
        return attractionRepository.save(existingAttraction);
    }

    public List<Picture> getPicturesInfo(Integer attractionId) {
        return pictureRepository.findByAttractionId(attractionId);
    }
}

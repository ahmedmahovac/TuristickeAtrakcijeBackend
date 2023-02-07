package com.example.touristAttractions.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Attraction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Integer id;
}

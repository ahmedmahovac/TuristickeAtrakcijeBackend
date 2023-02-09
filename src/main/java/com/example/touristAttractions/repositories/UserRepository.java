package com.example.touristAttractions.repositories;

import com.example.touristAttractions.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
}

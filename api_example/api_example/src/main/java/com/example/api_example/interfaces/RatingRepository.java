package com.example.api_example.interfaces;

import com.example.api_example.model.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingRepository {
    List<Rating> findAll();
    Optional<Rating> findById(Integer id);
    Rating save(Rating rating);
    Rating update(Rating rating);
    boolean deleteById(Integer id);
}
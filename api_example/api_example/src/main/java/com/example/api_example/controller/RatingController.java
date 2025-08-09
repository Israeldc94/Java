package com.example.api_example.controller;


import com.example.api_example.interfaces.RatingRepository;
import com.example.api_example.model.Genre;
import com.example.api_example.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("api/ratings")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> ratings = ratingRepository.findAll();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Integer id){
        Optional<Rating> rating = ratingRepository.findById((id));
        return rating.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating created = ratingRepository.save(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Integer id, @RequestBody Rating rating){
        Optional<Rating> existingRating = ratingRepository.findById(id);

        if (existingRating.isPresent()) {
            rating.setRatingId(id);
            Rating updatedRating = ratingRepository.save(rating); // Changed from update() to save()
            return ResponseEntity.ok(updatedRating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteGenre (@PathVariable Integer id) {
        boolean deleted = ratingRepository.deleteById(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }




}

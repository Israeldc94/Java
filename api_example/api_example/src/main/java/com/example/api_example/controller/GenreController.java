package com.example.api_example.controller;


import com.example.api_example.interfaces.GenreRepository;
import com.example.api_example.interfaces.MovieRepository;
import com.example.api_example.model.Genre;
import com.example.api_example.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres(){
        List<Genre> genres = genreRepository.findAll();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getMovieById(@PathVariable Integer id){
        Optional<Genre> genre = genreRepository.findById((id));
        return genre.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Genre> createMovie(@RequestBody Genre genre) {
        Genre created = genreRepository.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateMovie(@PathVariable Integer id, @RequestBody Genre genre){
        Optional<Genre> existingGenre = genreRepository.findById(id);

        if (existingGenre.isPresent()) {
            genre.setGenreId(id);
            Genre updatedGenre = genreRepository.save(genre); // Changed from update() to save()
            return ResponseEntity.ok(updatedGenre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteGenre (@PathVariable Integer id) {
        boolean deleted = genreRepository.deleteById(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }




}

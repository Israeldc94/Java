package com.example.api_example.interfaces;



import com.example.api_example.model.Movie;
import com.example.api_example.model.MovieSummary;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    List<Movie> findAll();
    Optional<Movie> findById(Integer id);
    Movie save(Movie movie);
    Movie update(Movie movie);
    boolean deleteById(Integer id);
    List<MovieSummary> findAllMovieSummaries();
    Optional<MovieSummary> findMovieSummaryById(Integer id);
}

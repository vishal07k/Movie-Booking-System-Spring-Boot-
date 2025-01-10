package com.movieApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieApp.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
    public Movie findByMovieName(String name);
}

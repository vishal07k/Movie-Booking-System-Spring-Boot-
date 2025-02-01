package com.movieApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieApp.entities.MoviePoster;

public interface PosterRepository extends JpaRepository<MoviePoster, String>{

}

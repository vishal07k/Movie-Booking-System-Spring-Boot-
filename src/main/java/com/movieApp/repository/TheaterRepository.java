package com.movieApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieApp.entities.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByAddress(String address);
}

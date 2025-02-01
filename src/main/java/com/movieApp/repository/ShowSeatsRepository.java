package com.movieApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieApp.entities.ShowSeat;

public interface ShowSeatsRepository extends JpaRepository<ShowSeat, Integer>{

}

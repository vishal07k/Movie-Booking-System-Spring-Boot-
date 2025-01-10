package com.movieApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieApp.entities.Ticket;


public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}

package com.movieApp.convertor;

import com.movieApp.entities.Show;
import com.movieApp.entities.Ticket;
import com.movieApp.response.HistoryResponse;
import com.movieApp.response.TicketResponse;

public class TicketConvertor {
	 public static TicketResponse returnTicket(Show show, Ticket ticket) {
		 
		 TicketResponse ticketResponseDto = new TicketResponse();
		 ticketResponseDto.setBookedSeats(ticket.getBookedSeats());
		 ticketResponseDto.setAddress(show.getTheater().getAddress());
		 ticketResponseDto.setTheaterName(show.getTheater().getName());
		 ticketResponseDto.setMovieName(show.getMovie().getMovieName());
		 ticketResponseDto.setDate(show.getDate());
		 ticketResponseDto.setTime(show.getTime());
		 ticketResponseDto.setTotalPrice(ticket.getTotalTicketsPrice());
		 
		 return ticketResponseDto;
	 }
	 
	 public static HistoryResponse returnHistory(Ticket ticket) {
		 
		 HistoryResponse history = new HistoryResponse();
		 history.setName(ticket.getUser().getName());
		 history.setTime(ticket.getShow().getTime());
		 history.setDate(ticket.getBookedAt());
		 history.setMovieName(ticket.getShow().getMovie().getMovieName());
		 history.setTheaterName(ticket.getShow().getTheater().getName());
		 history.setBookedSeats(ticket.getBookedSeats());
		 history.setTotalPrice(ticket.getTotalTicketsPrice());
		 
		 return history;
		 
	 }
}

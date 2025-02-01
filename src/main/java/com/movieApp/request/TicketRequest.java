package com.movieApp.request;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequest {
	
	private Integer movieId;
    private Integer showId;
    private Integer userId;
    private List<String> requestSeats;
	public Integer getShowId() {
		return showId;
	}
	
	
	public Integer getMovieId() {
		return movieId;
	}


	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}


	public void setShowId(Integer showId) {
		this.showId = showId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<String> getRequestSeats() {
		return requestSeats;
	}
	public void setRequestSeats(List<String> requestSeats) {
		this.requestSeats = requestSeats;
	}
	@Override
	public String toString() {
		return "TicketRequest [showId=" + showId + ", userId=" + userId + ", requestSeats=" + requestSeats + "]";
	}
    
}

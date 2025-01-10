package com.movieApp.response;

import java.sql.Date;
import java.sql.Time;

public class HistoryResponse {
 
		private String name; 
		private Time time;
	    private Date date;
	    private String movieName;
	    private String theaterName;
	    private String bookedSeats;
	    private Integer totalPrice;
	    
		public Time getTime() {
			return time;
		}
		public void setTime(Time time) {
			this.time = time;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getMovieName() {
			return movieName;
		}
		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}
		public String getTheaterName() {
			return theaterName;
		}
		public void setTheaterName(String theaterName) {
			this.theaterName = theaterName;
		}
		
		public String getBookedSeats() {
			return bookedSeats;
		}
		public void setBookedSeats(String bookedSeats) {
			this.bookedSeats = bookedSeats;
		}
		public Integer getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(Integer totalPrice) {
			this.totalPrice = totalPrice;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	    
}

package com.movieApp.request;

import lombok.Data;

@Data
public class TheaterSeatRequest {
    private String address;
    private Integer noOfSeatInRow;
    private Integer noOfPremiumSeat;
    private Integer noOfClassicSeat;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getNoOfSeatInRow() {
		return noOfSeatInRow;
	}
	public void setNoOfSeatInRow(Integer noOfSeatInRow) {
		this.noOfSeatInRow = noOfSeatInRow;
	}
	public Integer getNoOfPremiumSeat() {
		return noOfPremiumSeat;
	}
	public void setNoOfPremiumSeat(Integer noOfPremiumSeat) {
		this.noOfPremiumSeat = noOfPremiumSeat;
	}
	public Integer getNoOfClassicSeat() {
		return noOfClassicSeat;
	}
	public void setNoOfClassicSeat(Integer noOfClassicSeat) {
		this.noOfClassicSeat = noOfClassicSeat;
	}
    
    
}

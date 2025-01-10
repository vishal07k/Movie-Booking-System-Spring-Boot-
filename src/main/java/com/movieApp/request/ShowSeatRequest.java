package com.movieApp.request;

import lombok.Data;

@Data
public class ShowSeatRequest {
    private Integer showId;
    private Integer priceOfPremiumSeat;
    private Integer priceOfClassicSeat;
	public Integer getShowId() {
		return showId;
	}
	public void setShowId(Integer showId) {
		this.showId = showId;
	}
	public Integer getPriceOfPremiumSeat() {
		return priceOfPremiumSeat;
	}
	public void setPriceOfPremiumSeat(Integer priceOfPremiumSeat) {
		this.priceOfPremiumSeat = priceOfPremiumSeat;
	}
	public Integer getPriceOfClassicSeat() {
		return priceOfClassicSeat;
	}
	public void setPriceOfClassicSeat(Integer priceOfClassicSeat) {
		this.priceOfClassicSeat = priceOfClassicSeat;
	}
    
    
}

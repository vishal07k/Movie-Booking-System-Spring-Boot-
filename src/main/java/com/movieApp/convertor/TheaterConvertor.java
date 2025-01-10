package com.movieApp.convertor;

import com.movieApp.entities.Theater;
import com.movieApp.request.TheaterRequest;

public class TheaterConvertor {

	public static Theater theaterDtoToTheater(TheaterRequest theaterRequest) {
		Theater theater = new Theater();
		theater.setName(theaterRequest.getName());
		theater.setAddress(theaterRequest.getAddress());
		
		return theater;
	}
}

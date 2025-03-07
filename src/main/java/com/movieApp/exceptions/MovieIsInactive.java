package com.movieApp.exceptions;

public class MovieIsInactive extends RuntimeException {

	public MovieIsInactive() {
		super("Movie is Currently Inactive ");
	}

}

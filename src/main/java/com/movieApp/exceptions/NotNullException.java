package com.movieApp.exceptions;

public class NotNullException extends RuntimeException{

	public NotNullException() {
		super("Do not leave field null or empty !");
	}

}

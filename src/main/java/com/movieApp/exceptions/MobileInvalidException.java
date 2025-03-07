package com.movieApp.exceptions;

public class MobileInvalidException extends RuntimeException{

	public MobileInvalidException() {
		super("Please Enter Valid Mobile Number");
	}

}

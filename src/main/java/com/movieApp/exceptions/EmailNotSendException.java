package com.movieApp.exceptions;

public class EmailNotSendException extends RuntimeException{

	public EmailNotSendException() {
		super("Email server error");
	}

}

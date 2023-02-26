package com.ameya.moviemicroservice.exception.movie;

public class MovieAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public MovieAlreadyExistsException() {
		super();
	}
	
	public MovieAlreadyExistsException(String errors) {
		super(errors);
	}

}

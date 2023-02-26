package com.ameya.schedulemicroservice.exception.movie;

public class MovieNotActiveException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public MovieNotActiveException() {
		super();
	}
	
	public MovieNotActiveException(String errors) {
		super(errors);
	}

}

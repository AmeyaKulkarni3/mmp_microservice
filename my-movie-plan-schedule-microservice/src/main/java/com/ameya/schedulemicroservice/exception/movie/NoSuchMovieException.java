package com.ameya.schedulemicroservice.exception.movie;

public class NoSuchMovieException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public NoSuchMovieException() {
		super();
	}
	
	public NoSuchMovieException(String errors) {
		super(errors);
	}

}

package com.ameya.schedulemicroservice.exception.genre;

public class NoSuchGenreException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public NoSuchGenreException() {
		super();
	}
	
	public NoSuchGenreException(String errors) {
		super(errors);
	}

}

package com.ameya.schedulemicroservice.exception.theater;

public class TheaterAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public TheaterAlreadyExistsException() {
		super();
	}
	
	public TheaterAlreadyExistsException(String errors) {
		super(errors);
	}

}

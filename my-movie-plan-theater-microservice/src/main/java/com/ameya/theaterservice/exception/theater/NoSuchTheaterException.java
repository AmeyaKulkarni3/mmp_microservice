package com.ameya.theaterservice.exception.theater;

public class NoSuchTheaterException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public NoSuchTheaterException() {
		super();
	}
	
	public NoSuchTheaterException(String errors) {
		super(errors);
	}

}

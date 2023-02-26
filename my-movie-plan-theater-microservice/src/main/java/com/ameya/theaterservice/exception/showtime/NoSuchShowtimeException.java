package com.ameya.theaterservice.exception.showtime;

public class NoSuchShowtimeException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public NoSuchShowtimeException() {
		super();
	}
	
	public NoSuchShowtimeException(String errors) {
		super(errors);
	}

}

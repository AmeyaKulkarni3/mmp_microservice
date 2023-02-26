package com.ameya.schedulemicroservice.exception.showtime;

public class ShowtimeAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public ShowtimeAlreadyExistsException() {
		super();
	}
	
	public ShowtimeAlreadyExistsException(String errors) {
		super(errors);
	}

}

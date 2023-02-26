package com.ameya.theaterservice.exception.seat;

public class SeatAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public SeatAlreadyExistsException() {
		super();
	}
	
	public SeatAlreadyExistsException(String errors) {
		super(errors);
	}

}

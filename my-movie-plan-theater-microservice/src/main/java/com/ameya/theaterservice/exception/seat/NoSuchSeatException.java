package com.ameya.theaterservice.exception.seat;

public class NoSuchSeatException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public NoSuchSeatException() {
		super();
	}
	
	public NoSuchSeatException(String errors) {
		super(errors);
	}

}

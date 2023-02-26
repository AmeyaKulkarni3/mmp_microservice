package com.ameya.schedulemicroservice.exception.schedule;

public class CantScheduleShowException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public CantScheduleShowException() {
		super();
	}
	
	public CantScheduleShowException(String errors) {
		super(errors);
	}

}

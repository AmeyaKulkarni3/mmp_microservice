package com.ameya.schedulemicroservice.exception.schedule;

public class NoSuchScheduleException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public NoSuchScheduleException() {
		super();
	}
	
	public NoSuchScheduleException(String errors) {
		super(errors);
	}

}

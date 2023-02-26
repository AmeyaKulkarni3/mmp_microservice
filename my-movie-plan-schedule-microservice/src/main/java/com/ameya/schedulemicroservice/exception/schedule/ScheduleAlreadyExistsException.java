package com.ameya.schedulemicroservice.exception.schedule;

public class ScheduleAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public ScheduleAlreadyExistsException() {
		super();
	}
	
	public ScheduleAlreadyExistsException(String errors) {
		super(errors);
	}

}

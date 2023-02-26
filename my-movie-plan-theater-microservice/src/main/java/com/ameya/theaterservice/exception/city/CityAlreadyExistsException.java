package com.ameya.theaterservice.exception.city;

public class CityAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public CityAlreadyExistsException() {
		super();
	}
	
	public CityAlreadyExistsException(String errors) {
		super(errors);
	}

}

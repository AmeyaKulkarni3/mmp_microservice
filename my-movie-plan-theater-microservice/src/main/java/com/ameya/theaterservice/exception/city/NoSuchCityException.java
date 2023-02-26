package com.ameya.theaterservice.exception.city;

public class NoSuchCityException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public NoSuchCityException() {
		super();
	}
	
	public NoSuchCityException(String errors) {
		super(errors);
	}

}

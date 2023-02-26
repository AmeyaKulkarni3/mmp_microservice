package com.ameya.theaterservice.exception.partner;

public class NoSuchPartnerException extends Exception {

	private static final long serialVersionUID = -826785633809971140L;

	public NoSuchPartnerException() {
		super();
	}
	
	public NoSuchPartnerException(String errors) {
		super(errors);
	}

}

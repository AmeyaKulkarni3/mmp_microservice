package com.ameya.theaterservice.exception.partner;

public class PartnerAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -826785633809971140L;

	public PartnerAlreadyExistsException() {
		super();
	}
	
	public PartnerAlreadyExistsException(String errors) {
		super(errors);
	}

}

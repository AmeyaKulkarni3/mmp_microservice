package com.ameya.schedulemicroservice.exception.tier;

public class TierAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public TierAlreadyExistsException() {
		super();
	}
	
	public TierAlreadyExistsException(String errors) {
		super(errors);
	}

}

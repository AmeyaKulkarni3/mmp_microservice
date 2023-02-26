package com.ameya.schedulemicroservice.exception.tier;

public class NoSuchTierException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public NoSuchTierException() {
		super();
	}
	
	public NoSuchTierException(String errors) {
		super(errors);
	}

}

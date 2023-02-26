package com.ameya.schedulemicroservice.exception.language;

public class NoSuchLanguageException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public NoSuchLanguageException() {
		super();
	}
	
	public NoSuchLanguageException(String errors) {
		super(errors);
	}

}

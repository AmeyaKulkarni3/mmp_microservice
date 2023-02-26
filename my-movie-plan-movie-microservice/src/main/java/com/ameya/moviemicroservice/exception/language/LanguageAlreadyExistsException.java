package com.ameya.moviemicroservice.exception.language;

public class LanguageAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public LanguageAlreadyExistsException() {
		super();
	}
	
	public LanguageAlreadyExistsException(String errors) {
		super(errors);
	}

}

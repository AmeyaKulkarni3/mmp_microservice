package com.ameya.moviemicroservice.exception.genre;

public class GenreAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public GenreAlreadyExistsException() {
		super();
	}
	
	public GenreAlreadyExistsException(String errors) {
		super(errors);
	}

}

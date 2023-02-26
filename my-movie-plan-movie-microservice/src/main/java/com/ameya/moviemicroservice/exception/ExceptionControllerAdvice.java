package com.ameya.moviemicroservice.exception;

import java.util.stream.Collectors;

import com.ameya.moviemicroservice.exception.genre.GenreAlreadyExistsException;
import com.ameya.moviemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.moviemicroservice.exception.language.LanguageAlreadyExistsException;
import com.ameya.moviemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.moviemicroservice.exception.movie.MovieAlreadyExistsException;
import com.ameya.moviemicroservice.exception.movie.NoSuchMovieException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@Autowired
	private Environment environment;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(Exception ex){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(environment.getProperty(ExceptionConstants.GENERAL_EXCEPTION.toString()));
		return new ResponseEntity<>(error, HttpStatus.OK);
		
	}
	
	private ErrorMessage generateException(Exception ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return error;
	}

	@ExceptionHandler(NoSuchGenreException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(NoSuchGenreException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(GenreAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(GenreAlreadyExistsException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoSuchLanguageException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(NoSuchLanguageException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(LanguageAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(LanguageAlreadyExistsException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}

	@ExceptionHandler(NoSuchMovieException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(NoSuchMovieException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MovieAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(MovieAlreadyExistsException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) 
	{
		 ErrorMessage error = new ErrorMessage();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     error.setMessage(ex.getBindingResult().getAllErrors()
	    		 		  	.stream().map(ObjectError::getDefaultMessage)
	    		 		  	.collect(Collectors.joining(", ")));
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstraintValidationExceptions(ConstraintViolationException ex) 
	{
		 ErrorMessage error = new ErrorMessage();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     error.setMessage(ex.getConstraintViolations()
	    		 			.stream().map(ConstraintViolation::getMessage)
	    		 			.collect(Collectors.joining(", ")));
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}

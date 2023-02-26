package com.ameya.theaterservice.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.ameya.theaterservice.exception.city.CityAlreadyExistsException;
import com.ameya.theaterservice.exception.city.NoSuchCityException;
import com.ameya.theaterservice.exception.partner.NoSuchPartnerException;
import com.ameya.theaterservice.exception.partner.PartnerAlreadyExistsException;
import com.ameya.theaterservice.exception.seat.NoSuchSeatException;
import com.ameya.theaterservice.exception.seat.SeatAlreadyExistsException;
import com.ameya.theaterservice.exception.showtime.NoSuchShowtimeException;
import com.ameya.theaterservice.exception.showtime.ShowtimeAlreadyExistsException;
import com.ameya.theaterservice.exception.theater.NoSuchTheaterException;
import com.ameya.theaterservice.exception.theater.TheaterAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
	
	@ExceptionHandler(CityAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(CityAlreadyExistsException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoSuchCityException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(NoSuchCityException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoSuchShowtimeException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(NoSuchShowtimeException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ShowtimeAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(ShowtimeAlreadyExistsException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoSuchTheaterException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(NoSuchTheaterException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(TheaterAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(TheaterAlreadyExistsException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(PartnerAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(PartnerAlreadyExistsException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoSuchPartnerException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(NoSuchPartnerException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoSuchSeatException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(NoSuchSeatException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(SeatAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(SeatAlreadyExistsException ex){
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

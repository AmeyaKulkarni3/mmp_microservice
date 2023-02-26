package com.ameya.schedulemicroservice.exception;

import java.util.stream.Collectors;

import com.ameya.schedulemicroservice.exception.city.CityAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.city.NoSuchCityException;
import com.ameya.schedulemicroservice.exception.genre.GenreAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.schedulemicroservice.exception.language.LanguageAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.schedulemicroservice.exception.movie.MovieAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.movie.MovieNotActiveException;
import com.ameya.schedulemicroservice.exception.movie.NoSuchMovieException;
import com.ameya.schedulemicroservice.exception.partner.NoSuchPartnerException;
import com.ameya.schedulemicroservice.exception.partner.PartnerAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.schedule.CantScheduleShowException;
import com.ameya.schedulemicroservice.exception.schedule.NoSuchScheduleException;
import com.ameya.schedulemicroservice.exception.schedule.ScheduleAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.seat.NoSuchSeatException;
import com.ameya.schedulemicroservice.exception.seat.SeatAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.showtime.NoSuchShowtimeException;
import com.ameya.schedulemicroservice.exception.showtime.ShowtimeAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.theater.NoSuchTheaterException;
import com.ameya.schedulemicroservice.exception.theater.TheaterAlreadyExistsException;

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
	
	@ExceptionHandler(NoSuchScheduleException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(NoSuchScheduleException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ScheduleAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(ScheduleAlreadyExistsException ex){
		ErrorMessage error = generateException(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(CantScheduleShowException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(CantScheduleShowException ex){
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
	
	@ExceptionHandler(MovieNotActiveException.class)
	public ResponseEntity<ErrorMessage> exceptionHandlerCustom(MovieNotActiveException ex){
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

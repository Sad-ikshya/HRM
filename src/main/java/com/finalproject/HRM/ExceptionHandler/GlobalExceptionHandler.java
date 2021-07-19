package com.springdataproject.bookstore.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex)
	{
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> notValidException(MethodArgumentNotValidException ex)
	{
		return new ResponseEntity<String>("Validation Error :"+ ex.getBindingResult().getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<String> illegalStateException(IllegalStateException ex)
	{
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exception(Exception ex)
	{
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

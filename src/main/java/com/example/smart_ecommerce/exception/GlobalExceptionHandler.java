package com.example.smart_ecommerce.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;
import com.example.smart_ecommerce.exception.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
		return new ErrorResponse(400, ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField()+ ": "+  error.getDefaultMessage()).collect(Collectors.joining("; ")),
				java.time.LocalDateTime.now().toString());
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ErrorResponse handleProductNotFoundException(ProductNotFoundException ex) {
		return new ErrorResponse(404, ex.getMessage(), java.time.LocalDateTime.now().toString());
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ErrorResponse handleCategoryNotFoundException(CategoryNotFoundException ex) {
		return new ErrorResponse(404, ex.getMessage(), java.time.LocalDateTime.now().toString());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
		return new ErrorResponse(404, ex.getMessage(), java.time.LocalDateTime.now().toString());
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ErrorResponse handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
		return new ErrorResponse(404, ex.getMessage(), java.time.LocalDateTime.now().toString());
	}
	
}
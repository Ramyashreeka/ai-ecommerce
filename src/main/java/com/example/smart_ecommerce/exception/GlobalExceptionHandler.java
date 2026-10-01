package com.example.smart_ecommerce.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@ExceptionHandler(CartAlreadyExistsException.class)
	public ResponseEntity handleCartAlreadyExistsException(CartAlreadyExistsException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity handleCartNotFoundException(CartNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(CartItemNotFoundException.class)
	public ResponseEntity<String> handleCartItemNotFoundException(CartItemNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(OrderItemNotFoundException.class)
	public ResponseEntity<String> handleOrderItemNotFoundException(OrderItemNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	
	
	
	
}
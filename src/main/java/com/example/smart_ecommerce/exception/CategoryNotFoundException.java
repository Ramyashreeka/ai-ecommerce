package com.example.smart_ecommerce.exception;

public class CategoryNotFoundException extends RuntimeException{
	
	public CategoryNotFoundException(String message) {
		super(message);
	}

}

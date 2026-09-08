package com.example.smart_ecommerce.dto;

import jakarta.validation.constraints.*;

public class ProductRequest {
	@NotBlank(message = "Product name is required")
	private String name;
	@Positive(message = "Price must be greater than 0")
	private double price;
	private String description;
	@PositiveOrZero(message = "Stock cannot be negative")
	private int stock;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

}

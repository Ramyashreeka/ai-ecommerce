package com.example.smart_ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private String description;
	private int stock;
	@ManyToOne @JoinColumn(name = "category_id")
	private Category category;
	
	public Product() {
	}
	
	public Product(Long id, String name, double price, String description, int stock, Category category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.category=category;
	}
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getStock() {
		return stock;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		this.price=price;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setStock(int stock) {
		this.stock= stock;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}

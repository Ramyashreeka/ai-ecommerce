package com.example.smart_ecommerce.service;

import org.springframework.stereotype.Service;
import com.example.smart_ecommerce.entity.Product;
import com.example.smart_ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	public java.util.List<Product> getAllProducts(){
		return productRepository.findAll();
	}
}

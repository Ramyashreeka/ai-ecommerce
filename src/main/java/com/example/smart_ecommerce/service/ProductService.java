package com.example.smart_ecommerce.service;

import org.springframework.stereotype.Service;
import com.example.smart_ecommerce.entity.Product;
import com.example.smart_ecommerce.repository.ProductRepository;
import com.example.smart_ecommerce.dto.ProductRequest;
import com.example.smart_ecommerce.dto.ProductResponse;

import java.util.Optional;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	public ProductResponse saveProduct(ProductRequest request) {
		Product product = new Product(null,
									request.getName(),
									request.getPrice(),
									request.getDescription(),
									request.getStock()
									);
											
		Product savedProduct = productRepository.save(product);
				ProductResponse response = new ProductResponse();
				
				response.setId(savedProduct.getId());
				response.setName(savedProduct.getName());
				response.setDescription(savedProduct.getDescription());
				response.setPrice(savedProduct.getPrice());
				response.setStock(savedProduct.getStock());
				
				return response;
	}
	public java.util.List<ProductResponse> getAllProducts(){
		java.util.List<Product> products = productRepository.findAll();
		java.util.List<ProductResponse> responses = new java.util.ArrayList<>();
				
				for(Product product:products) {
					ProductResponse response = new ProductResponse();
					response.setId(product.getId());
					response.setName(product.getName());
					response.setPrice(product.getPrice());
					response.setDescription(product.getDescription());
					response.setStock(product.getStock());
					
					responses.add(response);
				}
		return responses;
	}
	
	public Product updateProduct(Long id, Product updatedProduct) {
		Optional<Product> existingProduct = productRepository.findById(id);
		
		if(existingProduct.isPresent()) {
			Product product = existingProduct.get();//gets the existing product
			product.setName(updatedProduct.getName());//updating existing product details
			product.setPrice(updatedProduct.getPrice());
			product.setDescription(updatedProduct.getDescription());
			product.setStock(updatedProduct.getStock());
			
			return productRepository.save(product);//storing changes to mysql
			
		}
		return null;
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}

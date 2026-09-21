package com.example.smart_ecommerce.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.smart_ecommerce.entity.Category;
import com.example.smart_ecommerce.entity.Product;
import com.example.smart_ecommerce.repository.CategoryRepository;
import com.example.smart_ecommerce.repository.ProductRepository;
import com.example.smart_ecommerce.dto.CategoryResponse;
import com.example.smart_ecommerce.dto.ProductRequest;
import com.example.smart_ecommerce.dto.ProductResponse;
import com.example.smart_ecommerce.exception.CategoryNotFoundException;
import com.example.smart_ecommerce.exception.ProductNotFoundException;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	private CategoryRepository categoryRepository;
	
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository=productRepository;
		this.categoryRepository=categoryRepository;
	}
	public ProductResponse saveProduct(ProductRequest request) {
		Category category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException(
						"Category not found with id: " +request.getCategoryId()));
		Product product = new Product(null,
									request.getName(),
									request.getPrice(),
									request.getDescription(),
									request.getStock(),
									category
									);
											
		Product savedProduct = productRepository.save(product);
				return convertToResponse(savedProduct);
	}
	public java.util.List<ProductResponse> getAllProducts(){
		java.util.List<Product> products = productRepository.findAll();
		java.util.List<ProductResponse> responses = new java.util.ArrayList<>();
				
				for(Product product:products) {
					responses.add(convertToResponse(product));
				}
		return responses;
	}
	public Page<ProductResponse> getProductsWithPagination(Pageable pageable){
		Page<Product> products = productRepository.findAll(pageable);
		return products.map(product -> {
			return convertToResponse(product);
			
		});
		
	}
	
	public List<ProductResponse> getProductsSortedByPrice(){
		List<Product> products = productRepository.findAll(Sort.by("price").descending());
		List<ProductResponse> responses = new ArrayList<>();
		
		for(Product product: products) {
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
	
	public Page<ProductResponse> searchProducts(String name, Pageable pageable){
		
		Page<Product> products = productRepository.findByNameContainingIgnoreCase(name, pageable);
		return products.map(product -> {
			return convertToResponse(product);
			
		});
	
	}
	public ProductResponse getProductById(Long id) {
		Optional<Product> existingProduct = productRepository.findById(id);
		
		if(existingProduct.isPresent()) {
			Product product = existingProduct.get();//gets the existing product
			
			return convertToResponse(product);
		}
		throw new ProductNotFoundException("Product not found with id:" +id);
	}
	
	public ProductResponse updateProduct(Long id, ProductRequest request) {
		Optional<Product> existingProduct = productRepository.findById(id);
		
		if(existingProduct.isPresent()) {
			Product product = existingProduct.get();//gets the existing product
			product.setName(request.getName());//updating existing product details
			product.setPrice(request.getPrice());
			product.setDescription(request.getDescription());
			product.setStock(request.getStock());
			
			Product savedProduct = productRepository.save(product);
			
			ProductResponse response = new ProductResponse();
			
			response.setId(savedProduct.getId());
			response.setName(savedProduct.getName());
			response.setPrice(savedProduct.getPrice());
			response.setDescription(savedProduct.getDescription());
			response.setStock(savedProduct.getStock());
			
			
			return response;//storing changes to mysql
			
		}
		throw new ProductNotFoundException("Product not found with id: " +id);
	}
	
	public void deleteProduct(Long id) {
		Optional<Product> existingProduct = productRepository.findById(id);
		if(existingProduct.isPresent()) {
			productRepository.deleteById(id);
			return;
		}
		throw new ProductNotFoundException("Product not found with id: " +id);
	}
	
	private ProductResponse convertToResponse(Product product) {
		ProductResponse response = new ProductResponse();
		
		response.setId(product.getId());
		response.setName(product.getName());
		response.setPrice(product.getPrice());
		response.setDescription(product.getDescription());
		response.setStock(product.getStock());
		
		if(product.getCategory() != null) {
			
			CategoryResponse categoryResponse = new CategoryResponse();
			categoryResponse.setId(product.getCategory().getId());
			categoryResponse.setName(product.getCategory().getName());
			categoryResponse.setDescription(product.getCategory().getDescription());
			response.setCategory(categoryResponse);
		}
		
		
		return response;//storing changes to mysql
		
	}
}

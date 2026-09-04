package com.example.smart_ecommerce.service;

import org.springframework.stereotype.Service;
import com.example.smart_ecommerce.entity.Product;
import com.example.smart_ecommerce.repository.ProductRepository;
import java.util.Optional;

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

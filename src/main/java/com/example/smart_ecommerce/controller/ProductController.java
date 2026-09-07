package com.example.smart_ecommerce.controller;


import org.springframework.web.bind.annotation.*;
import com.example.smart_ecommerce.entity.Product;
import com.example.smart_ecommerce.service.ProductService;
import com.example.smart_ecommerce.dto.ProductRequest;
import com.example.smart_ecommerce.dto.ProductResponse;



@RestController
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/products")
	public java.util.List<ProductResponse> Products() {
		return productService.getAllProducts();
	}
	
	@PostMapping("/products")
	public ProductResponse saveProduct(@RequestBody ProductRequest request) {
		return productService.saveProduct(request);
	}
	
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "Product deleted successfully";
	}
	
	
	
	
	
	

}

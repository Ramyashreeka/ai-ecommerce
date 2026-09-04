package com.example.smart_ecommerce.controller;


import org.springframework.web.bind.annotation.*;
import com.example.smart_ecommerce.entity.Product;
import com.example.smart_ecommerce.service.ProductService;


@RestController
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/products")
	public java.util.List<Product> Products() {
		return productService.getAllProducts();
	}
	
	@PostMapping("/products")
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
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

package com.example.smart_ecommerce.controller;


import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.smart_ecommerce.service.ProductService;
import com.example.smart_ecommerce.dto.ProductRequest;
import com.example.smart_ecommerce.dto.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.*;





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
	
	@GetMapping("/products/{id}")
	public ProductResponse getProductById(@PathVariable Long id) {
		ProductResponse response = productService.getProductById(id);
		return productService.getProductById(id);
	}
	
	@GetMapping("/products/search")
	public Page<ProductResponse> searchProducts(@RequestParam String name, Pageable pageable){
		return productService.searchProducts(name, pageable);
	}
	
	@GetMapping("/products/sorted")
	public List<ProductResponse> getProductsSortedByPrice(){
		return productService.getProductsSortedByPrice();
	}
	@GetMapping("/products/page")
	public Page<ProductResponse> getProductsWithPagination(
			@RequestParam(defaultValue = "0")int page,
			@RequestParam(defaultValue="10")int size,
			@RequestParam(defaultValue="price")String sortBy,
			@RequestParam(defaultValue="asc")String direction){
		if(!sortBy.equals("price")&& !sortBy.equals("name")&& !sortBy.equals("stock")){
			sortBy="price";
		}
		if(size >50) {
			size=50;
		}
		if(page < 0) {
			page=0;
		}
		Sort sort;
		if(direction.equalsIgnoreCase("desc")) {
			sort = Sort.by(sortBy).descending();}
		else {
			sort = Sort.by(sortBy).ascending();
		}
		Pageable pageable = PageRequest.of(page, size, sort);
			
		return productService.getProductsWithPagination(pageable);
	}
	
	@PostMapping("/products")
	public ProductResponse saveProduct(@Valid @RequestBody ProductRequest request) {
		return productService.saveProduct(request);
	}
	
	@PutMapping("/products/{id}")
	public ProductResponse updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
		return productService.updateProduct(id, request);
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	

}

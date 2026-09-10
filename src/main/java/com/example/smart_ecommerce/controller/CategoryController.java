package com.example.smart_ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_ecommerce.dto.CategoryRequest;
import com.example.smart_ecommerce.dto.CategoryResponse;
import com.example.smart_ecommerce.service.CategoryService;
@RestController
public class CategoryController {
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@PostMapping("/categories")
	public CategoryResponse saveCategory(@RequestBody CategoryRequest request) {
		return categoryService.saveCategory(request);
	}
	
	@GetMapping("/categories")
	public List<CategoryResponse> getAllCategories(){
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/categories/{id}")
	public CategoryResponse getCategoryById(@PathVariable Long id){
		return categoryService.getCategoryById(id);
	}
	
	@PutMapping("/categories/{id}")
	public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request) {
		return categoryService.updateCategory(id, request);
	}
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
	
}

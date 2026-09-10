package com.example.smart_ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.smart_ecommerce.dto.CategoryRequest;
import com.example.smart_ecommerce.dto.CategoryResponse;
import com.example.smart_ecommerce.entity.Category;
import com.example.smart_ecommerce.exception.CategoryNotFoundException;
import com.example.smart_ecommerce.repository.CategoryRepository;
@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public CategoryResponse saveCategory(CategoryRequest request) {
		Category category = new Category(null, request.getName(), request.getDescription());
		Category savedCategory = categoryRepository.save(category);
		return convetToResponse(savedCategory);
	}
	
	public List<CategoryResponse> getAllCategories(){
		List<Category> categories = categoryRepository.findAll();
		
		List<CategoryResponse> responses = new ArrayList<>();
		for(Category category:categories) {
			
			responses.add(convetToResponse(category));
			
		}
		return responses;
	}
	
	public CategoryResponse getCategoryById(Long id) {
		Optional<Category> existingCategory = categoryRepository.findById(id);
		if(existingCategory.isPresent()) {
			Category category = existingCategory.get();
			return convetToResponse(category);
		}
		throw new CategoryNotFoundException("Category not found with id: "+id);
	}
	
	public CategoryResponse updateCategory(Long id,CategoryRequest request) {
		
		Optional<Category> existingCategory = categoryRepository.findById(id);
		if(existingCategory.isPresent()) {
		Category category = existingCategory.get();
		category.setName(request.getName());
		category.setDescription(request.getDescription());
		Category savedCategory = categoryRepository.save(category);
		return convetToResponse(savedCategory);
	}
		throw new CategoryNotFoundException("Category not found with id: "+id);
	}
	
public void deleteCategory(Long id) {
		
		Optional<Category> existingCategory = categoryRepository.findById(id);
		if(existingCategory.isPresent()) {
		categoryRepository.deleteById(id);
		return;
	}
		throw new CategoryNotFoundException("Category not found with id: "+id);
	}

private CategoryResponse convetToResponse(Category category) {
	CategoryResponse response = new CategoryResponse();
	response.setId(category.getId());
	response.setName(category.getName());
	response.setDescription(category.getDescription());
	return response;
}
	
	
}

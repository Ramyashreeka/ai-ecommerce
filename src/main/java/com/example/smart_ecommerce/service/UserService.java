package com.example.smart_ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.smart_ecommerce.dto.UserResponse;
import com.example.smart_ecommerce.entity.User;
import com.example.smart_ecommerce.repository.UserRepository;
import com.example.smart_ecommerce.exception.EmailAlreadyExistsException;
import com.example.smart_ecommerce.exception.UserNotFoundException;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserResponse createUser(User user) {
		//checking email uniqueness
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		User savedUser = userRepository.save(user);
		UserResponse response = new UserResponse();
		response.setId(savedUser.getId());
		response.setName(savedUser.getName());
		response.setEmail(savedUser.getEmail());
		
		return response;
		
	}
	
	public UserResponse getUserById(Long id) {
		
		User user =userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		
		return response;
	}
	
	public List<UserResponse> getAllUsers(){
		
		List<User> users = userRepository.findAll();
		List<UserResponse> responses = new ArrayList<>();
		for (User user:users){
			UserResponse response = new UserResponse();
			response.setId(user.getId());
			response.setName(user.getName());
			response.setEmail(user.getEmail());
			
			responses.add(response);
		}
			return responses;

	}
	
	public UserResponse updateUser(Long id, User user) {
		
		User existingUser = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
		
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		
		User savedUser = userRepository.save(existingUser);
		UserResponse response = new UserResponse();
		response.setId(savedUser.getId());
		response.setName(savedUser.getName());
		response.setEmail(savedUser.getEmail());
		
		return response;
	}
	
	public void deleteUser(Long id) {
		User existingUser = userRepository.findById(id). orElseThrow(() -> new UserNotFoundException("User not found"));
		userRepository.delete(existingUser);
	}
}

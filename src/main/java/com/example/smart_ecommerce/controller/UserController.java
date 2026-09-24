package com.example.smart_ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_ecommerce.dto.UserResponse;
import com.example.smart_ecommerce.entity.User;
import com.example.smart_ecommerce.service.UserService;

import jakarta.validation.Valid;

@RestController @RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody User user){
		UserResponse response = userService.createUser(user);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUSerById(@PathVariable Long id){
		UserResponse response =userService.getUserById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponse>>getAllUsers(){
		List<UserResponse> responses = userService.getAllUsers();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@Valid @PathVariable Long id, @RequestBody User user){
		
		UserResponse response = userService.updateUser(id,  user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

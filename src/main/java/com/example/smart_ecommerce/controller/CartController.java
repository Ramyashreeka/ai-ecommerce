package com.example.smart_ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_ecommerce.dto.CartResponse;
import com.example.smart_ecommerce.service.CartService;

@RestController @RequestMapping("/api/carts")
public class CartController {
	
	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@PostMapping
	public ResponseEntity<CartResponse> createCart(@RequestParam Long userId){
		CartResponse response = cartService.createCart(userId);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CartResponse> getCartById(@PathVariable Long id){
		CartResponse response = cartService.getCartById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<CartResponse> getCartByUserId(@PathVariable Long userId){
		CartResponse response = cartService.getCartByUserId(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}

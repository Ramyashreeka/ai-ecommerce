package com.example.smart_ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.smart_ecommerce.dto.CartItemResponse;
import com.example.smart_ecommerce.service.CartItemService;


@RestController @RequestMapping("/api/cart-items")
public class CartItemController {
	
	private final CartItemService cartItemService;

	public CartItemController(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
	@PostMapping
	public ResponseEntity<CartItemResponse> addItemToCart(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam Integer quantity){
		CartItemResponse response = cartItemService.addItemToCart(cartId, productId, quantity);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CartItemResponse> getCartItemById(@PathVariable Long id){
		CartItemResponse response = cartItemService.getCartItemById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/cart/{cartId}")
	public ResponseEntity<List<CartItemResponse>> getItemsByCartId(@PathVariable Long cartId){
		List<CartItemResponse> responses = cartItemService.getItemsByCartId(cartId);
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CartItemResponse> updateQuantity(@PathVariable Long id, @RequestParam Integer quantity){
		CartItemResponse response = cartItemService.updateQuantity(id, quantity);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeCartItem(@PathVariable Long id){
		cartItemService.removeCartItem(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/cart/{cartId}/total")
	public ResponseEntity<Double> getCartTotal(@PathVariable Long cartId){
		Double total = cartItemService.getCartTotal(cartId);
		return new ResponseEntity<>(total, HttpStatus.OK);
	}

}

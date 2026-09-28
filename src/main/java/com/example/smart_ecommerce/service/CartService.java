package com.example.smart_ecommerce.service;

import org.springframework.stereotype.Service;

import com.example.smart_ecommerce.dto.CartResponse;
import com.example.smart_ecommerce.entity.Cart;
import com.example.smart_ecommerce.entity.User;
import com.example.smart_ecommerce.exception.*;
import com.example.smart_ecommerce.repository.CartRepository;
import com.example.smart_ecommerce.repository.UserRepository;

@Service
public class CartService {

	private final CartRepository cartRepository;
	private final UserRepository userRepository;
	
	public CartService(CartRepository cartRepository, UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
	}
	
	public CartResponse createCart(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
		
		//duplicate cart check
		if(cartRepository.findByUser_Id(userId).isPresent()) {
			throw new CartAlreadyExistsException("Cart already exists for this user");
		}
		Cart cart = new Cart(); //create cart
		cart.setUser(user);//Attach user to the cart
		
		Cart savedCart = cartRepository.save(cart);//save cart
		CartResponse response = new CartResponse();
		response.setId(savedCart.getId());
		response.setUserId(savedCart.getUser().getId());
		
		return response;//return cart response
	}
	
	public CartResponse getCartById(Long id) {
		
		Cart cart = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException("Cart not found"));
		CartResponse response = new CartResponse();
		response.setId(cart.getId());
		response.setUserId(cart.getUser().getId());
		return response;
		
	}
	
	//get cart by user
	public CartResponse getCartByUserId(Long userId) {
		Cart cart = cartRepository.findByUser_Id(userId).orElseThrow(()-> new CartNotFoundException("Cart not found"));
		CartResponse response = new CartResponse();
		response.setId(cart.getId());
		response.setUserId(cart.getUser().getId());
		return response;
	}
	
	
	
}

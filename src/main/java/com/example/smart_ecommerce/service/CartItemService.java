package com.example.smart_ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.smart_ecommerce.dto.CartItemResponse;
import com.example.smart_ecommerce.entity.Cart;
import com.example.smart_ecommerce.entity.CartItem;
import com.example.smart_ecommerce.entity.Product;
import com.example.smart_ecommerce.exception.*;
import com.example.smart_ecommerce.repository.CartItemRepository;
import com.example.smart_ecommerce.repository.CartRepository;
import com.example.smart_ecommerce.repository.ProductRepository;

@Service
public class CartItemService {
	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;

	public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductRepository productRepository) {
		this.cartItemRepository = cartItemRepository;
		this.cartRepository = cartRepository;
		this.productRepository=productRepository;
	}
	
	public CartItemResponse addItemToCart(Long cartId, Long productId, Integer quantity) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart not found"));
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found"));
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		CartItem savedItem = cartItemRepository.save(cartItem);
		CartItemResponse response = new CartItemResponse();
		response.setId(savedItem.getId());
		response.setCartId(savedItem.getCart().getId());
		response.setProductId(savedItem.getProduct().getId());
		response.setQuantity(savedItem.getQuantity());
		
		return response;
		
	}
	
	public CartItemResponse getCartItemById(Long id) {
		CartItem cartItem = cartItemRepository.findById(id).orElseThrow(()->new CartItemNotFoundException("Cart item not found"));
		CartItemResponse response= new CartItemResponse();
		response.setId(cartItem.getId());
		response.setCartId(cartItem.getCart().getId());
		response.setProductId(cartItem.getProduct().getId());
		response.setQuantity(cartItem.getQuantity());
		return response;
		
	}
	
	public List<CartItemResponse> getItemByCartId(Long cartId){
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found"));
		
		List<CartItem> cartItems = cartItemRepository.findByCart_Id(cartId);
		List<CartItemResponse> responses = new ArrayList<>();
		for(CartItem cartItem:cartItems) {
			CartItemResponse response = new CartItemResponse();
			response.setId(cartItem.getId());
			response.setCartId(cartItem.getCart().getId());
			response.setProductId(cartItem.getProduct().getId());
			response.setQuantity(cartItem.getQuantity());
			
			responses.add(response);
		}
		
		return responses;
		
	}
	
	public List<CartItemResponse> getItemsByCartId(Long cartId){
		Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart not found"));
		List<CartItem> cartItems =cartItemRepository.findByCart_Id(cartId);
		List<CartItemResponse> responses = new ArrayList<>();
		for(CartItem cartItem:cartItems) {
			CartItemResponse response = new CartItemResponse();
			response.setId(cartItem.getId());
			response.setCartId(cartItem.getCart().getId());
			response.setProductId(cartItem.getProduct().getId());
			response.setQuantity(cartItem.getQuantity());
			responses.add(response);
		}
		return responses;
		
	}
	
	public CartItemResponse updateQuantity(Long id, Integer quantity) {
		CartItem cartItem = cartItemRepository.findById(id).orElseThrow(()->new CartItemNotFoundException("Cart item not found"));
		cartItem.setQuantity(quantity);
		
		CartItem updatedItem=cartItemRepository.save(cartItem);
		
		CartItemResponse response = new CartItemResponse();
		response.setId(updatedItem.getId());
		response.setCartId(updatedItem.getCart().getId());
		response.setProductId(updatedItem.getProduct().getId());
		response.setQuantity(updatedItem.getQuantity());
		
		return response;
		
	}
	
	public void removeCartItem(Long id) {
		CartItem cartItem = cartItemRepository.findById(id).orElseThrow(()->new CartItemNotFoundException("Cart item not found"));
		cartItemRepository.delete(cartItem);
	}
	
	//cart total
	public Double getCartTotal(Long cartId) {
		Cart cart =cartRepository.findById(cartId).orElseThrow(()->new CartNotFoundException("Cart not found"));
		
		List<CartItem> cartItems = cartItemRepository.findByCart_Id(cartId);
		double total = 0;
		for(CartItem cartItem:cartItems) {
			double price = cartItem.getProduct().getPrice();
			int quantity=cartItem.getQuantity();
			total = total +(price * quantity);
		}
		return total;
	}
	
}

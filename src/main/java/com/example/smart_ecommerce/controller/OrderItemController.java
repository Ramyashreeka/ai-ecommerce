package com.example.smart_ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_ecommerce.dto.OrderItemResponse;
import com.example.smart_ecommerce.dto.OrderResponse;
import com.example.smart_ecommerce.service.OrderItemService;

@RestController @RequestMapping("/api/order-items")
public class OrderItemController {
	private final OrderItemService orderItemService;

	public OrderItemController(OrderItemService orderItemService) {
		super();
		this.orderItemService = orderItemService;
	}
	
	@PostMapping
	public ResponseEntity<OrderItemResponse> createOrderItem(@RequestParam Long orderId, @RequestParam Long productId, @RequestParam Integer quantity){
		OrderItemResponse response = orderItemService.createOrderItem(orderId, productId, quantity);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderItemResponse> getOrderItemById(@PathVariable Long id){
		OrderItemResponse response = orderItemService.getOrderItemById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("order/{orderId}")
	public ResponseEntity<List<OrderItemResponse>> getItemsByOrderId(@PathVariable Long orderId){
		List<OrderItemResponse> responses = orderItemService.getItemsByOrderId(orderId);
		return new ResponseEntity<>(responses, HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderItemResponse> updateQuantity(@PathVariable Long id, @RequestParam Integer quantity){
		OrderItemResponse response = orderItemService.updateQuantity(id, quantity);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<OrderItemResponse> removeOrderItem(@PathVariable Long id){
		orderItemService.removeOrderItem(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	@GetMapping
	public ResponseEntity<List<OrderItemResponse>> getAllOrderItems(){
		List<OrderItemResponse> responses = orderItemService.getAllOrderItems();
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
}

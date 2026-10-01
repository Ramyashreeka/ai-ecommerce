package com.example.smart_ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_ecommerce.dto.OrderResponse;
import com.example.smart_ecommerce.service.OrderService;

@RestController @RequestMapping("/api/orders")
public class OrderController {
	private final OrderService orderService;
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
		
	}
	
	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@RequestParam Long userId){
		OrderResponse response = orderService.createOrder(userId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id){
		OrderResponse response = orderService.getOrderById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<OrderResponse>> getAllOrders(){
		List<OrderResponse> responses = orderService.getAllOrders();
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable Long id, @RequestParam String status){
		OrderResponse response = orderService.updateOrderStatus(id, status);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/checkout")
	public ResponseEntity<OrderResponse> checkout(@RequestParam Long userId){
		OrderResponse response = orderService.checkout(userId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
}

package com.example.smart_ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_ecommerce.dto.PaymentResponse;
import com.example.smart_ecommerce.service.PaymentService;

@RestController @RequestMapping("/api/payments")
public class PaymentController {
	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@PostMapping
	public ResponseEntity<PaymentResponse> createPayment(@RequestParam Long orderId, @RequestParam String paymentMethod){
		PaymentResponse response = paymentService.createPayment(orderId,  paymentMethod);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id){
		PaymentResponse response = paymentService.getPaymentById(id);
		return ResponseEntity.ok(response);//returns payment details for payment id
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<PaymentResponse> getPaymentByOrderId(@PathVariable Long orderId){
		PaymentResponse response = paymentService.getPaymentByOrderId(orderId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<PaymentResponse>> getAllPayments(){
		List<PaymentResponse> responses = paymentService.getAllPayments();
		paymentService.getAllPayments();
		return ResponseEntity.ok(responses);//return all payment records
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<PaymentResponse> updatePaymentStatus(@PathVariable Long id, @RequestParam String status){
		PaymentResponse response = paymentService.updatePaymentStatus(id, status);
		return ResponseEntity.ok(response);
	}
}

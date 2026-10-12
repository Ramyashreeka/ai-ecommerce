package com.example.smart_ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smart_ecommerce.dto.PaymentResponse;
import com.example.smart_ecommerce.entity.Order;
import com.example.smart_ecommerce.entity.Payment;
import com.example.smart_ecommerce.exception.PaymentAlreadyExistsException;
import com.example.smart_ecommerce.repository.OrderRepository;
import com.example.smart_ecommerce.repository.PaymentRepository;
import com.example.smart_ecommerce.exception.*;

@Service
public class PaymentService {
	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;
	public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
		this.paymentRepository = paymentRepository;
		this.orderRepository = orderRepository;
	}
	public PaymentResponse createPayment(Long orderId, String paymentMethod) {
		if(paymentMethod == null || paymentMethod.isBlank()){
			throw new IllegalArgumentException("Payment method is required");
		}
		if(paymentRepository.findByOrder_Id(orderId).isPresent()) {
			throw new PaymentAlreadyExistsException("Payment already exists for this order");
		}
		Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order not found"));
		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setAmount(order.getTotalAmount());//amount taking from order
		payment.setPaymentMethod(paymentMethod);
		payment.setStatus("PENDING");
		Payment savedPayment = paymentRepository.save(payment);
		PaymentResponse response = new PaymentResponse();
		response.setId(savedPayment.getId());
		response.setOrderId(savedPayment.getOrder().getId());
		response.setAmount(savedPayment.getAmount());
		response.setPaymentMethod(savedPayment.getPaymentMethod());
		response.setStatus(savedPayment.getStatus());
		return response;
	}
	
	public PaymentResponse getPaymentById(Long id) {
		Payment payment = paymentRepository.findById(id).orElseThrow(()->new PaymentNotFoundException("Payment not found"));
		PaymentResponse response = new PaymentResponse();
		response.setId(payment.getId());
		response.setOrderId(payment.getOrder().getId());
		response.setAmount(payment.getAmount());
		response.setPaymentMethod(payment.getPaymentMethod());
		response.setStatus(payment.getStatus());
		return response;
	}
	
	//get payment by order id
	public PaymentResponse getPaymentByOrderId(Long orderId) {
		
		Payment payment = paymentRepository.findByOrder_Id(orderId).orElseThrow(()-> new PaymentNotFoundException("Payment not found"));{
		PaymentResponse response = new PaymentResponse();
		response.setId(payment.getId());
		response.setOrderId(payment.getOrder().getId());
		response.setAmount(payment.getAmount());
		response.setPaymentMethod(payment.getPaymentMethod());
		response.setStatus(payment.getStatus());
		return response;
		}
		
	}
	
	//get all payments
	public List<PaymentResponse> getAllPayments(){
		return paymentRepository.findAll().stream().map(payment->{
		PaymentResponse response = new PaymentResponse();
		response.setId(payment.getId());
		response.setOrderId(payment.getOrder().getId());
		response.setAmount(payment.getAmount());
		response.setPaymentMethod(payment.getPaymentMethod());
		response.setStatus(payment.getStatus());
		return response;}).toList();
		
	}
	
	//update payment status
	public PaymentResponse updatePaymentStatus(Long id, String status) {
		//to reject unsupported status values
		if(status==null || (!status.equals("PENDING") && !status.equals("SUCCESS") && !status.equals("FAILED"))) {
			throw new IllegalArgumentException("Invalid payment status");
		}
		Payment payment = paymentRepository.findById(id).orElseThrow(()-> new PaymentNotFoundException("Payment not found"));
		payment.setStatus(status);
		Payment savedPayment = paymentRepository.save(payment);
		PaymentResponse response = new PaymentResponse();
		response.setId(savedPayment.getId());
		response.setOrderId(savedPayment.getOrder().getId());
		response.setAmount(savedPayment.getAmount());
		response.setPaymentMethod(savedPayment.getPaymentMethod());
		response.setStatus(savedPayment.getStatus());
		return response;
	}
	
}

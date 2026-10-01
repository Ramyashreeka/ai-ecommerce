package com.example.smart_ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smart_ecommerce.dto.CartItemResponse;
import com.example.smart_ecommerce.dto.OrderResponse;
import com.example.smart_ecommerce.entity.Order;
import com.example.smart_ecommerce.entity.User;
import com.example.smart_ecommerce.exception.UserNotFoundException;
import com.example.smart_ecommerce.repository.OrderRepository;
import com.example.smart_ecommerce.repository.UserRepository;
import com.example.smart_ecommerce.exception.OrderNotFoundException;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final CartService cartService;
	private final CartItemService cartItemService;
	private final OrderItemService orderItemService;
	
	public OrderService(OrderRepository orderRepository, UserRepository userRepository, CartService cartService, CartItemService cartItemService, OrderItemService orderItemService) {
		this.orderRepository = orderRepository;
		this.userRepository=userRepository;
		this.cartService=cartService;
		this.cartItemService = cartItemService;
		this.orderItemService=orderItemService;
	}
	
	public OrderResponse createOrder(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found "));//find the user
		Long cartId = cartService.getCartByUserId(userId).getId();
		Double totalAmount = cartItemService.getCartTotal(cartId);//find that user cart and calculate order total
		Order order = new Order();//creates an order
		order.setUser(user);
		order.setTotalAmount(totalAmount);
		order.setStatus("PLACED");
		Order savedOrder = orderRepository.save(order);//saves the order
		OrderResponse response = new OrderResponse();
		response.setId(savedOrder.getId());
		response.setUserId(savedOrder.getUser().getId());
		response.setTotalAmount(savedOrder.getTotalAmount());
		response.setStatus(savedOrder.getStatus());
		return response;//returns order id, user id, total amount
		
	}
	public OrderResponse getOrderById(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("Order not found"));
		OrderResponse response = new OrderResponse();
		response.setId(order.getId());
		response.setUserId(order.getUser().getId());
		response.setTotalAmount(order.getTotalAmount());
		response.setStatus(order.getStatus());
		return response;
	}
	
	//get all orders
	public List<OrderResponse> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderResponse> responses = new ArrayList();
		for(Order order: orders) {
			OrderResponse response = new OrderResponse();
			response.setId(order.getId());
			response.setUserId(order.getUser().getId());
			response.setTotalAmount(order.getTotalAmount());
			response.setStatus(order.getStatus());
			responses.add(response);
		}
		return responses;
	}
	
	//order status update
	public OrderResponse updateOrderStatus(Long id, String status) {
		Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order not found"));
		order.setStatus(status);
		Order savedOrder = orderRepository.save(order);
		OrderResponse response = new OrderResponse();
		response.setId(savedOrder.getUser().getId());
		response.setUserId(savedOrder.getUser().getId());
		response.setTotalAmount(savedOrder.getTotalAmount());
		response.setStatus(savedOrder.getStatus());
		return response;
	}
	
	//order checkout
	public OrderResponse checkout(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
		Long cartId = cartService.getCartByUserId(userId).getId();
		List<CartItemResponse> cartItems = cartItemService.getItemsByCartId(cartId);
		if(cartItems.isEmpty()) {
			throw new IllegalArgumentException("Cart is empty");
		}
		double total = cartItemService.getCartTotal(cartId);
		Order order = new Order();
		order.setUser(user);
		order.setTotalAmount(total);
		order.setStatus("PLACED");
		
		Order savedOrder = orderRepository.save(order);
		//connect cart items to the newly created order.
		for(CartItemResponse cartItem: cartItems) {
			orderItemService.createOrderItem(savedOrder.getId(), cartItem.getProductId(), cartItem.getQuantity());
			cartItemService.clearCart(cartId);
		}
		
		OrderResponse response = new OrderResponse();
		response.setId(savedOrder.getId());
		response.setUserId(savedOrder.getUser().getId());
		response.setTotalAmount(savedOrder.getTotalAmount());
		response.setStatus(savedOrder.getStatus());
		return response;
		
	}
}

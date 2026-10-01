package com.example.smart_ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smart_ecommerce.dto.OrderItemResponse;
import com.example.smart_ecommerce.entity.Order;
import com.example.smart_ecommerce.entity.OrderItem;
import com.example.smart_ecommerce.entity.Product;
import com.example.smart_ecommerce.exception.*;
import com.example.smart_ecommerce.repository.OrderItemRepository;
import com.example.smart_ecommerce.repository.OrderRepository;
import com.example.smart_ecommerce.repository.ProductRepository;

@Service
public class OrderItemService {
	
	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository,
			ProductRepository productRepository) {
		super();
		this.orderItemRepository = orderItemRepository;
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}
	
	public OrderItemResponse createOrderItem(Long orderId, Long productId, Integer quantity) {
		if(quantity <=0) {
			throw new IllegalArgumentException("Quantity must be greater than zero.");
		}
		Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order not found"));
		
		Product product = productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product not found"));
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		orderItem.setProduct(product);
		orderItem.setQuantity(quantity);
		orderItem.setPrice(product.getPrice());
		OrderItem savedOrderItem = orderItemRepository.save(orderItem);
		OrderItemResponse response = new OrderItemResponse();
		response.setId(savedOrderItem.getId());
		response.setOrderId(savedOrderItem.getOrder().getId());
		response.setProductId(savedOrderItem.getProduct().getId());
		response.setQuantity(savedOrderItem.getQuantity());
		response.setPrice(savedOrderItem.getPrice());
		return response;
		
	}
	//get an order item by id
	public OrderItemResponse getOrderItemById(Long id) {
		OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(()-> new OrderItemNotFoundException("Order item not found"));
		OrderItemResponse response = new OrderItemResponse();
		response.setId(orderItem.getId());
		response.setOrderId(orderItem.getOrder().getId());
		response.setProductId(orderItem.getProduct().getId());
		response.setQuantity(orderItem.getQuantity());
		response.setPrice(orderItem.getPrice());
		
		return response;
	}
	
	//get all items for an order
	public List<OrderItemResponse> getItemsByOrderId(Long orderId) {
		List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(orderId);
		List<OrderItemResponse> responses = new ArrayList<>();
		for(OrderItem orderItem : orderItems) {			
			OrderItemResponse response = new OrderItemResponse();
			response.setId(orderItem.getId());
			response.setOrderId(orderItem.getOrder().getId());
			response.setProductId(orderItem.getProduct().getId());
			response.setQuantity(orderItem.getQuantity());
			response.setPrice(orderItem.getPrice());
			
			responses.add(response);
		}
		return responses;
	}
	
	//update quantity
	public OrderItemResponse updateQuantity(Long id, Integer quantity) {
		if(quantity <=0) {
			throw new IllegalArgumentException("Quantity must be greater than zero.");
		}
		OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(()-> new OrderItemNotFoundException("Order item not found"));
		orderItem.setQuantity(quantity);
		Order order = orderItem.getOrder();
		List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(order.getId());
		double total =0.0;
		for (OrderItem item: orderItems) {
			if(item.getId().equals(orderItem.getId())) {
				total += item.getPrice()* quantity;
			}else {
				total += item.getPrice()* item.getQuantity();
			}
			order.setTotalAmount(total);
			orderRepository.save(order);
		}
		OrderItem savedOrderItem = orderItemRepository.save(orderItem);
		OrderItemResponse response = new OrderItemResponse();
		response.setId(orderItem.getId());
		response.setOrderId(orderItem.getOrder().getId());
		response.setProductId(orderItem.getProduct().getId());
		response.setQuantity(orderItem.getQuantity());
		response.setPrice(orderItem.getPrice());
		return response;
		
	}
	
	//delete order item //handle order item deletion and order total
	public void removeOrderItem(Long id) {
		OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(()->new OrderItemNotFoundException("Order item not found"));
		Order order = orderItem.getOrder();
		orderItemRepository.delete(orderItem);
		List<OrderItem> remainingItems = orderItemRepository.findByOrder_Id(order.getId());
		double total =0.0;
		for(OrderItem item: remainingItems) {
			total+=item.getPrice()*item.getQuantity();
		}
		order.setTotalAmount(total);
		orderRepository.save(order);
	}
	
	//get all order items
	public List<OrderItemResponse> getAllOrderItems() {
		List<OrderItem> orderItems = orderItemRepository.findAll();
		List<OrderItemResponse> responses = new ArrayList<>();
		for(OrderItem orderItem : orderItems) {			
			OrderItemResponse response = new OrderItemResponse();
			response.setId(orderItem.getId());
			response.setOrderId(orderItem.getOrder().getId());
			response.setProductId(orderItem.getProduct().getId());
			response.setQuantity(orderItem.getQuantity());
			response.setPrice(orderItem.getPrice());
			
			responses.add(response);
		}
		return responses;
	}
	
	
	
	
}

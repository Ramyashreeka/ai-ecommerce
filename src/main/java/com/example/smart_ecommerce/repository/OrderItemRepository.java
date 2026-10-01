package com.example.smart_ecommerce.repository;

import java.util.List;

import com.example.smart_ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
	List<OrderItem> findByOrder_Id(Long orderId);//to retrive all products belonging to a particular order.

}

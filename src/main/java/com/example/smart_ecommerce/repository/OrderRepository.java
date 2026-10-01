package com.example.smart_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smart_ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	

}

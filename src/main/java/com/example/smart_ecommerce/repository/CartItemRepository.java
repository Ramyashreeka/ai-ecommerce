package com.example.smart_ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smart_ecommerce.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	List<CartItem> findByCart_Id(Long cartId);
}

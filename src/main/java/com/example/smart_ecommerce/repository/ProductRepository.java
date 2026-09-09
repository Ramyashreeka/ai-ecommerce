package com.example.smart_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;
import java.util.List;
import com.example.smart_ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

}

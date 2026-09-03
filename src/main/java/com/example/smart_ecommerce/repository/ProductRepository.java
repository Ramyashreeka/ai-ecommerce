package com.example.smart_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart_ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}

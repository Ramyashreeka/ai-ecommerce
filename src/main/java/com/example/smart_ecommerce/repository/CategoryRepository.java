package com.example.smart_ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart_ecommerce.entity.Category;
public interface CategoryRepository extends JpaRepository<Category, Long>{

}

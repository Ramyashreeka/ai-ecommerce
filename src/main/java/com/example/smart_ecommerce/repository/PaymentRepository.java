package com.example.smart_ecommerce.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart_ecommerce.entity.Payment;
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	Optional<Payment> findByOrder_Id(Long orderId);//using associated order id find a payment
}

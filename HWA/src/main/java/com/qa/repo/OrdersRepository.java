package com.qa.repo;

import com.qa.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

    Order findByOrderId(Long orderId);

}
package com.qa.repo;

import com.qa.domain.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderlinesRepository extends JpaRepository<Orderline, Long> {

    Orderline findByOrderId(Long orderId);

}
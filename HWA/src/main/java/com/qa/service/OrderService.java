package com.qa.service;

import com.qa.domain.Order;
import com.qa.dto.OrderDTO;
import com.qa.exceptions.OrderNotFoundException;
import com.qa.repo.OrdersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrdersRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public OrderService(OrdersRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private OrderDTO mapToDTO(Order order) {
        return this.mapper.map(order, OrderDTO.class);
    }

    public List<OrderDTO> readOrders() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public OrderDTO createOrder(Order order) {
        Order tempOrder = this.repo.save(order);
        return this.mapToDTO(tempOrder);
    }

    public OrderDTO findOrderById(Long orderId) {
        return this.mapToDTO(this.repo.findById(orderId).orElseThrow(OrderNotFoundException::new));
    }

    public OrderDTO updateOrder(Long orderId, Order order) {
        Order update = this.repo.findById(orderId).orElseThrow(OrderNotFoundException::new);
        update.setCustomerId(order.getCustomerId());
        update.setOrderValue(order.getOrderValue());
        Order tempOrder = this.repo.save(update);
        return this.mapToDTO(tempOrder);
    }

    public boolean deleteOrder(Long orderId){
        if(!this.repo.existsById(orderId)){
            throw new OrderNotFoundException();
        }
        this.repo.deleteById(orderId);
        return this.repo.existsById(orderId);
    }
}
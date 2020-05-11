package com.qa.service;

import com.qa.domain.Orderline;
import com.qa.dto.OrderlineDTO;
import com.qa.exceptions.OrderlineNotFoundException;
import com.qa.repo.OrderlinesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderlineService {

    private final OrderlinesRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public OrderlineService(OrderlinesRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private OrderlineDTO mapToDTO(Orderline orderline) {
        return this.mapper.map(orderline, OrderlineDTO.class);
    }

    public List<OrderlineDTO> readOrderlines() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public OrderlineDTO createOrderline(Orderline orderline) {
        Orderline tempOrderline = this.repo.save(orderline);
        return this.mapToDTO(tempOrderline);
    }

    public OrderlineDTO findOrderlineById(Long orderId) {
        return this.mapToDTO(this.repo.findById(orderId).orElseThrow(OrderlineNotFoundException::new));
    }

    public OrderlineDTO updateOrderline(Long orderId, Orderline orderline) {
        Orderline update = this.repo.findById(orderId).orElseThrow(OrderlineNotFoundException::new);
        update.setOrderId(orderline.getOrderId());
        update.setCardId(orderline.getCardId());
        Orderline tempOrderline = this.repo.save(update);
        return this.mapToDTO(tempOrderline);
    }

    public boolean deleteOrderline(Long orderId){
        if(!this.repo.existsById(orderId)){
            throw new OrderlineNotFoundException();
        }
        this.repo.deleteById(orderId);
        return this.repo.existsById(orderId);
    }
}
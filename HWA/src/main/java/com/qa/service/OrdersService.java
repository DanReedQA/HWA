package com.qa.service;

import com.qa.domain.Customer;
import com.qa.domain.Orders;
import com.qa.dto.OrderDTO;
import com.qa.exceptions.OrdersNotFoundException;
import com.qa.repo.CustomersRepository;
import com.qa.repo.OrdersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final CustomersRepository customersRepository;

    private final ModelMapper mapper;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, CustomersRepository customersRepository, ModelMapper mapper) {
        this.ordersRepository = ordersRepository;
        this.customersRepository = customersRepository;
        this.mapper = mapper;
    }

    public OrderDTO mapToDTO(Orders orders) {
        return this.mapper.map(orders, OrderDTO.class);
    }

    public OrderDTO createOrder(Orders orders) {
        return this.mapToDTO(this.ordersRepository.save(orders));
    }

    public boolean deleteOrder(Long orderId) {
        if (!this.ordersRepository.existsById(orderId)) {
            throw new OrdersNotFoundException();
        }
        this.ordersRepository.deleteById(orderId);
        return this.ordersRepository.existsById(orderId);
    }

    public OrderDTO findOrderById(Long orderId) {
        return this.mapToDTO(this.ordersRepository.findById(orderId).orElseThrow(() -> new OrdersNotFoundException()));
    }

    public List<OrderDTO> readOrders() {
        return this.ordersRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public OrderDTO updateOrder(Orders customer, Long orderId) {
        Orders toUpdate = this.ordersRepository.findById(orderId).orElseThrow(() -> new OrdersNotFoundException());
        toUpdate.setCustomers(customer.getCustomers());
        return this.mapToDTO(this.ordersRepository.save(toUpdate));
    }

}
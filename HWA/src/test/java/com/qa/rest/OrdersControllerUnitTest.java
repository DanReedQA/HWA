package com.qa.rest;

import com.qa.domain.Orders;
import com.qa.dto.OrderDTO;
import com.qa.service.OrdersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerUnitTest {

    @InjectMocks
    private OrdersController ordersController;

    @Mock
    private OrdersService service;

    private List<Orders> orders;

    private Orders testOrders;

    private Orders testOrdersWithId;

    private long orderId = 1L;

    private OrderDTO orderDTO;

    private final ModelMapper mapper = new ModelMapper();

    private OrderDTO mapToDTO(Orders orders) {
        return this.mapper.map(orders, OrderDTO.class);
    }

    @Before
    public void setUp() {
        this.orders = new ArrayList<>();
        this.testOrders = new Orders(1L);
        this.orders.add(testOrders);
        this.testOrdersWithId = new Orders(testOrders.getOrderId());
        this.testOrdersWithId.setOrderId(this.orderId);
        this.orderDTO = this.mapToDTO(testOrdersWithId);
    }

    @Test
    public void getAllOrdersTest() {
        when(service.readOrders()).thenReturn(this.orders.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No orders found", this.ordersController.getAllOrders().getBody().isEmpty());
        verify(service, times(1)).readOrders();
    }

    @Test
    public void createOrderTest() {
        when(this.service.createOrder(testOrders)).thenReturn(this.orderDTO);
        assertEquals(this.ordersController.createOrder(testOrders), new ResponseEntity<OrderDTO>(this.orderDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createOrder(testOrders);
    }

    @Test
    public void deleteOrderTestFalse() {
        this.ordersController.deleteOrder(orderId);
        verify(service, times(1)).deleteOrder(orderId);
    }


    @Test
    public void deleteOrderTestTrue() {
        when(service.deleteOrder(3L)).thenReturn(true);
        this.ordersController.deleteOrder(3L);
        verify(service, times(1)).deleteOrder(3L);
    }

    @Test
    public void getOrderByIDTest() {
        when(this.service.findOrderById(orderId)).thenReturn(this.orderDTO);
        assertEquals(this.ordersController.getOrderById(orderId), new ResponseEntity<OrderDTO>(this.orderDTO, HttpStatus.OK));
        verify(service, times(1)).findOrderById(orderId);
    }

}
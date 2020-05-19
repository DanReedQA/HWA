package com.qa.service;

import com.qa.domain.Box;
import com.qa.domain.Orders;
import com.qa.dto.BoxDTO;
import com.qa.dto.OrderDTO;
import com.qa.exceptions.OrdersNotFoundException;
import com.qa.repo.OrdersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OrdersServiceUnitTest {

    @InjectMocks
    private OrdersService service;

    @Mock
    private OrdersRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<Orders> ordersList;

    private Orders testOrders;

    private long orderId = 1L;

    private Orders testOrdersWithID;

    private OrderDTO orderDTO;

    private OrderDTO mapToDTO(Orders orders) {
        return this.mapper.map(orders, OrderDTO.class);
    }

    @Before
    public void setUp() {
        this.ordersList = new ArrayList<>();
        this.testOrders = new Orders(1L);
        this.ordersList.add(testOrders);
        this.testOrdersWithID = new Orders();
        this.testOrdersWithID.setOrderId(orderId);
        this.orderDTO = this.mapToDTO(testOrdersWithID);
    }

    @Test
    public void getAllOrdersTest() {
        when(repository.findAll()).thenReturn(this.ordersList);
        when(this.mapper.map(testOrdersWithID, OrderDTO.class)).thenReturn(orderDTO);
        assertFalse("Service returned no Orders", this.service.readOrders().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createOrderTest() {
        when(repository.save(testOrders)).thenReturn(testOrdersWithID);
        when(this.mapper.map(testOrdersWithID, OrderDTO.class)).thenReturn(orderDTO);
        assertEquals(this.service.createOrder(testOrders), this.orderDTO);
        verify(repository, times(1)).save(this.testOrders);
    }

    @Test
    public void findOrderByIdTest() {
        when(this.repository.findById(orderId)).thenReturn(java.util.Optional.ofNullable(testOrdersWithID));
        when(this.mapper.map(testOrdersWithID, OrderDTO.class)).thenReturn(orderDTO);
        assertEquals(this.service.findOrderById(this.orderId), orderDTO);
        verify(repository, times(1)).findById(orderId);
    }

    @Test
    public void deleteOrderByExistingId() {
        when(this.repository.existsById(orderId)).thenReturn(true, false);
        assertFalse(service.deleteOrder(orderId));
        verify(repository, times(1)).deleteById(orderId);
        verify(repository, times(2)).existsById(orderId);
    }

    @Test(expected = OrdersNotFoundException.class)
    public void deleteOrderByNonExistingId() {
        when(this.repository.existsById(orderId)).thenReturn(false);
        service.deleteOrder(orderId);
        verify(repository, times(1)).existsById(orderId);
    }

    @Test
    public void updateOrderTest(){

        Orders newOrders = new Orders(1L);
        Orders updateOrders = new Orders(newOrders.getOrderId());
        updateOrders.setOrderId(orderId);

        OrderDTO updateOrderDTO = new ModelMapper().map(updateOrders, OrderDTO.class);

        when(this.repository.findById(orderId)).thenReturn(java.util.Optional.ofNullable(testOrdersWithID));
        when(this.repository.save(updateOrders)).thenReturn(updateOrders);
        when(this.mapper.map(updateOrders, OrderDTO.class)).thenReturn(updateOrderDTO);

        assertEquals(updateOrderDTO, this.service.updateOrder(newOrders, orderId));
        verify(this.repository, times(1)).findById(orderId);
        verify(this.repository, times(1)).save(updateOrders);
    }

}
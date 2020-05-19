package com.qa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Orders;
import com.qa.dto.OrderDTO;
import com.qa.repo.OrdersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrdersControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private OrdersRepository repository;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Orders testOrders;

    private Orders testOrdersWithID;

    private long orderId;

    private OrderDTO orderDTO;

    private OrderDTO mapToDTO(Orders orders){
        return this.mapper.map(orders, OrderDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testOrders = new Orders();
        this.testOrdersWithID = this.repository.save(testOrders);
        this.orderId = testOrdersWithID.getOrderId();
        this.orderDTO = this.mapToDTO(testOrdersWithID);
    }

    @Test
    public void getAllOrdersTest() throws Exception {
        List<OrderDTO> ordersDTOList = new ArrayList<>();
        ordersDTOList.add(orderDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllOrders")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(ordersDTOList));
    }

    @Test
    public void getOrderByID() throws Exception {
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getOrderById/" + this.orderId)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(orderDTO));
    }

    @Test
    public void createOrderTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testOrders))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(orderDTO));
    }

    @Test
    public void deleteOrderTest() throws Exception {
        this.mock.perform(
                request(HttpMethod.DELETE, "/deleteOrder/" + this.orderId)
        ).andExpect(status().isNoContent());
    }
}
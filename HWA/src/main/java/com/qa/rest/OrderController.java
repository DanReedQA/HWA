package com.qa.rest;

import com.qa.domain.Order;
import com.qa.dto.OrderDTO;
import com.qa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        super();
        this.service = service;
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(this.service.readOrders());
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDTO> createCard(@RequestBody Order order) {
        return new ResponseEntity<OrderDTO>(this.service.createOrder(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        return this.service.deleteOrder(orderId)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getOrderById/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(this.service.findOrderById(orderId));
    }

    @PutMapping("/updateOrder/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody Order order) {
        return ResponseEntity.ok(this.service.updateOrder(order, orderId));
    }

    @PutMapping("/updateOrder2")
    public ResponseEntity<OrderDTO> updateOrder2(@PathParam("orderId") Long orderId, @RequestBody Order order) {
        return ResponseEntity.ok(this.service.updateOrder(order, orderId));
    }

}
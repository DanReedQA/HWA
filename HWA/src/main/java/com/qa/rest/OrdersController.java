package com.qa.rest;

import com.qa.domain.Orders;
import com.qa.dto.OrderDTO;
import com.qa.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrdersController {

    private final OrdersService service;

    @Autowired
    public OrdersController(OrdersService service) {
        super();
        this.service = service;
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(this.service.readOrders());
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Orders orders) {
        return new ResponseEntity<OrderDTO>(this.service.createOrder(orders), HttpStatus.CREATED);
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
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody Orders orders) {
        return ResponseEntity.ok(this.service.updateOrder(orders, orderId));
    }

    @PutMapping("/updateOrder2")
    public ResponseEntity<OrderDTO> updateOrder2(@PathParam("orderId") Long orderId, @RequestBody Orders orders) {
        return ResponseEntity.ok(this.service.updateOrder(orders, orderId));
    }

}
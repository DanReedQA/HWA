package com.qa.rest;

import com.qa.domain.Orderline;
import com.qa.dto.OrderlineDTO;
import com.qa.service.OrderlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class OrderlineController {

    private final OrderlineService service;

    @Autowired
    public OrderlineController(OrderlineService service) {
        this.service = service;
    }

    @GetMapping("/getAllOrderlines")
    public ResponseEntity<List<OrderlineDTO>> getAllOrders() {
        return ResponseEntity.ok(this.service.readOrderlines());
    }

    @PostMapping("/createOrderline")
    public ResponseEntity<OrderlineDTO> createCard(@RequestBody Orderline orderline) {
        return new ResponseEntity<OrderlineDTO>(this.service.createOrderline(orderline), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteOrderline/{orderId}")
    public ResponseEntity<?> deleteOrderline(@PathVariable Long orderId) {
        return this.service.deleteOrderline(orderId)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getOrderlineById/{orderId}")
    public ResponseEntity<OrderlineDTO> getOrderlineById(@PathVariable Long orderId) {
        return ResponseEntity.ok(this.service.findOrderlineById(orderId));
    }

    @PutMapping("/updateOrderline/{orderId}")
    public ResponseEntity<OrderlineDTO> updateOrderline(@PathVariable Long orderId, @RequestBody Orderline orderline) {
        return ResponseEntity.ok(this.service.updateOrderline(orderId, orderline));
    }

    @PutMapping("/updateOrderline2")
    public ResponseEntity<OrderlineDTO> updateOrderline2(@PathParam("orderId") Long orderId, @RequestBody Orderline orderline) {
        return ResponseEntity.ok(this.service.updateOrderline(orderId, orderline));
    }

}
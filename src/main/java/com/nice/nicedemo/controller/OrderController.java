package com.nice.nicedemo.controller;

import com.nice.nicedemo.dto.NewOrder;
import com.nice.nicedemo.dto.Orders;
import com.nice.nicedemo.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody NewOrder newOrder) {
        orderService.createNewOrder(newOrder.getDishes());
    }

    @GetMapping
    public Orders getReadyOrders() {
        return orderService.getReadyOrders();
    }

    @DeleteMapping("/{id}")
    public void serveOrder(@PathVariable String id) {
        log.info("Serving order: {}", id);
        orderService.serveOrder(id);
    }
}

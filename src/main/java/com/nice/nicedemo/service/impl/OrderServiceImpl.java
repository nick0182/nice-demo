package com.nice.nicedemo.service.impl;

import com.nice.nicedemo.dto.Dish;
import com.nice.nicedemo.dto.Order;
import com.nice.nicedemo.dto.Orders;
import com.nice.nicedemo.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final Map<String, Dish> dishesInRestaurant;

    private final Map<String, Order> readyOrders;

    private final ExecutorService chefsInRestaurant;

    @Override
    public void createNewOrder(List<String> dishes) {
        log.info("Creating new order with dishes: {}", dishes);
        chefsInRestaurant.execute(new OrderExecution(dishes));
    }

    @Override
    public Orders getReadyOrders() {
        Orders result = Orders.builder().orders(readyOrders.values()).build();
        log.info("Getting ready orders: {}", result);
        return result;
    }

    @Override
    public void serveOrder(String id) {
        Order servedOrder = readyOrders.remove(id);
        log.info("Order served: {}", servedOrder);
    }

    @Override
    public void destroy() {
        log.info("Destroying executor");
        chefsInRestaurant.shutdownNow();
    }

    @Value
    class OrderExecution implements Runnable {

        List<String> dishes;

        @Override
        public void run() {
            log.info("Executing order with dishes: {}", dishes);
            dishes.forEach(dishId -> {
                int prepTimeMinutes = dishesInRestaurant.get(dishId).getPrepTimeMinutes();
                try {
                    // cook
                    int sleep = 10000;
                    log.info("Sleeping: {}", sleep);
                    log.info("Thread is: {}", Thread.currentThread().getName());
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            String orderId = UUID.randomUUID().toString();
            log.info("Created order with id: {}", orderId);
            readyOrders.put(orderId, Order.builder().id(orderId).dishes(dishes).ready(true).build());
        }
    }
}

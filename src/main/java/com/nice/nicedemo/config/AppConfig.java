package com.nice.nicedemo.config;

import com.nice.nicedemo.dto.Dish;
import com.nice.nicedemo.dto.Order;
import com.nice.nicedemo.service.OrderService;
import com.nice.nicedemo.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Value("${app.number-of-chefs}")
    private int numOfChefs;

    @Bean(destroyMethod = "destroy")
    OrderService orderService(Map<String, Dish> dishesInRestaurant, Map<String, Order> readyOrders, ExecutorService chefsInRestaurant) {
        return new OrderServiceImpl(dishesInRestaurant, readyOrders, chefsInRestaurant);
    }

    @Bean
    Map<String, Order> readyOrders() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    ExecutorService chefsInRestaurant() {
        return Executors.newFixedThreadPool(numOfChefs);
    }

    @Bean
    Map<String, Dish> dishesInRestaurant() {
        return Map.of("1",
                Dish.builder().id("1").prepTimeMinutes(30).price(15).build(),
                "2",
                Dish.builder().id("2").prepTimeMinutes(45).price(15).build(),
                "3",
                Dish.builder().id("3").prepTimeMinutes(15).price(15).build(),
                "4",
                Dish.builder().id("4").prepTimeMinutes(5).price(15).build(),
                "5",
                Dish.builder().id("5").prepTimeMinutes(10).price(15).build()
        );
    }
}

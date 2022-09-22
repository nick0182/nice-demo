package com.nice.nicedemo.service;

import com.nice.nicedemo.dto.Orders;
import org.springframework.beans.factory.DisposableBean;

import java.util.List;

public interface OrderService extends DisposableBean {

    void createNewOrder(List<String> dishes);

    Orders getReadyOrders();

    void serveOrder(String id);
}

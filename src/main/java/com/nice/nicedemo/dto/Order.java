package com.nice.nicedemo.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Order {
    String id;
    boolean ready;
    List<String> dishes;
}

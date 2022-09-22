package com.nice.nicedemo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Dish {
    String id;
    int prepTimeMinutes;
    double price;
}

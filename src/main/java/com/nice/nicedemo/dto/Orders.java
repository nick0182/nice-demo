package com.nice.nicedemo.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
public class Orders {
    Collection<Order> orders;
}

package com.nice.nicedemo.dto;

import lombok.*;

import java.util.List;

@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NewOrder {
    List<String> dishes;
}

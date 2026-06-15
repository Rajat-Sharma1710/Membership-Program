package com.example.membershipProgram.model.dto;

import com.example.membershipProgram.model.Order;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class OrderResponseDto {

    private final Long id;
    private final LocalDate orderDate;
    private final double orderPrice;
    private final Long user_id;
    private final String username;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.orderPrice = order.getOrderPrice();
        this.user_id = order.getUser().getId();
        this.username = order.getUser().getName();
    }
}

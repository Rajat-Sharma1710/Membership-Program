package com.example.membershipProgram.service;

import com.example.membershipProgram.model.Order;

public interface IOrderService {

    Order getOrderById(Long orderId);

    Order addOrder(Order order);
}

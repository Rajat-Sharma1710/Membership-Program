package com.example.membershipProgram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.membershipProgram.model.Order;
import com.example.membershipProgram.repository.OrderRepository;
import com.example.membershipProgram.service.IOrderService;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

}

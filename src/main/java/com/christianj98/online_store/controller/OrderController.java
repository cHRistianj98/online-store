package com.christianj98.online_store.controller;

import com.christianj98.online_store.dto.CreateOrderDto;
import com.christianj98.online_store.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public void createOrder(@RequestBody CreateOrderDto createOrderDto) {
        orderService.createOrder(createOrderDto);
    }
}

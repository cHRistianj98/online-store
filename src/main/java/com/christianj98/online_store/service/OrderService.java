package com.christianj98.online_store.service;

import com.christianj98.online_store.dto.CreateOrderDto;
import com.christianj98.online_store.entity.Order;
import com.christianj98.online_store.mapper.OrderMapper;
import com.christianj98.online_store.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public void createOrder(CreateOrderDto createOrderDto) {
        Order order = orderMapper.toEntity(createOrderDto);
        orderRepository.save(order);
    }
}

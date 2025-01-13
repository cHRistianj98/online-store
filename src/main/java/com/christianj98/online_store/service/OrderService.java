package com.christianj98.online_store.service;

import com.christianj98.online_store.dto.CreateOrderDto;
import com.christianj98.online_store.entity.Order;
import com.christianj98.online_store.mapper.OrderMapper;
import com.christianj98.online_store.producer.OrderProducer;
import com.christianj98.online_store.repository.OrderRepository;
import com.christianj98.online_store.schema.OrderKafkaRecord;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;

    public void createOrder(CreateOrderDto createOrderDto) {
        final OrderKafkaRecord kafkaRecord = orderMapper.toKafkaRecord(createOrderDto);
        orderProducer.sendOrder(kafkaRecord);
    }

    @Transactional
    public void processOrder(OrderKafkaRecord orderKafkaRecord) {
        Order order = orderMapper.toEntity(orderKafkaRecord);
        orderRepository.save(order);
    }
}

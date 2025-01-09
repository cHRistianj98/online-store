package com.christianj98.online_store.consumer;

import com.christianj98.online_store.schema.OrderKafkaRecord;
import com.christianj98.online_store.service.OrderService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    private final OrderService orderService;

    public OrderConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "orders", groupId = "orders-group")
    public void listenOrdersTopic(ConsumerRecord<String, OrderKafkaRecord> record) {
        String key = record.key();
        OrderKafkaRecord order = record.value();
        log.info("Received message: key={}, value={}", key, order);
        processOrder(order);
    }

    private void processOrder(OrderKafkaRecord orderKafkaRecord) {
        log.info("Processing order: {}", orderKafkaRecord);
        orderService.processOrder(orderKafkaRecord);
    }
}

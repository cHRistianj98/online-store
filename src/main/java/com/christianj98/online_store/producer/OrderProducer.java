package com.christianj98.online_store.producer;

import com.christianj98.online_store.schema.OrderKafkaRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderKafkaRecord> kafkaTemplate;

    @Value("${kafka.topic.orders}")
    private String ordersTopic;

    public OrderProducer(KafkaTemplate<String, OrderKafkaRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(OrderKafkaRecord order) {
        ProducerRecord<String, OrderKafkaRecord> record = new ProducerRecord<>(ordersTopic, null, order);
        kafkaTemplate.send(record);
    }
}
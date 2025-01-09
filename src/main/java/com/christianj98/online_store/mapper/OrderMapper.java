package com.christianj98.online_store.mapper;

import com.christianj98.online_store.dto.CreateOrderDto;
import com.christianj98.online_store.entity.Order;
import com.christianj98.online_store.schema.OrderKafkaRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(CreateOrderDto dto);

    Order toEntity(OrderKafkaRecord orderKafkaRecord);

    CreateOrderDto toDto(Order order);

    @Mapping(source = "orderId", target = "id")
    OrderKafkaRecord toKafkaRecord(CreateOrderDto dto);

    default String map(CharSequence value) {
        return value == null ? null : value.toString();
    }
}
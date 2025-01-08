package com.christianj98.online_store.mapper;

import com.christianj98.online_store.dto.CreateOrderDto;
import com.christianj98.online_store.entity.Order;
import com.christianj98.online_store.schema.OrderKafkaRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(CreateOrderDto dto);

    CreateOrderDto toDto(Order order);

    OrderKafkaRecord toKafkaRecord(Order order);

    default String map(CharSequence value) {
        return value == null ? null : value.toString();
    }
}
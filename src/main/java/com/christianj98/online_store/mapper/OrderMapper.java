package com.christianj98.online_store.mapper;

import com.christianj98.online_store.dto.CreateOrderDto;
import com.christianj98.online_store.entity.Order;
import com.christianj98.online_store.schema.OrderKafkaRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

//    @Mapping(source = "orderId", target = "id")
    Order toEntity(CreateOrderDto dto);

//    @Mapping(source = "id", target = "orderId")
    CreateOrderDto toDto(Order order);

    OrderKafkaRecord toKafkaRecord(Order order);

    default String map(CharSequence value) {
        return value == null ? null : value.toString();
    }
}
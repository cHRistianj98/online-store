package com.christianj98.online_store.dto;

public record CreateOrderDto(
        Integer orderId,
        String product,
        String quantity
) {
}

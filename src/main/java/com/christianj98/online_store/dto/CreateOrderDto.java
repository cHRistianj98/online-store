package com.christianj98.online_store.dto;

public record CreateOrderDto(
        String product,
        Integer quantity
) {
}

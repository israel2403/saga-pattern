package com.huerta.orders.shared.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.huerta.core.types.OrderStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderHistoryResponse {
    private UUID id;
    private UUID orderId;
    private OrderStatus status;
    private Timestamp createdAt;

    public OrderHistoryResponse(UUID id, UUID orderId, OrderStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}

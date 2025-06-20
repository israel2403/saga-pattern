package com.huerta.orders.shared.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.huerta.core.types.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderHistory {
    private UUID id;
    private UUID orderId;
    private OrderStatus status;
    private Timestamp createdAt;

    public OrderHistory(UUID id, UUID orderId, OrderStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}

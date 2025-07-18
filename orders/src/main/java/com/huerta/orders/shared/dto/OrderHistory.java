package com.huerta.orders.shared.dto;

import com.huerta.core.types.OrderStatus;
import java.time.LocalDateTime;
import java.util.UUID;
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
    private LocalDateTime createdAt;

    public OrderHistory(UUID id, UUID orderId, OrderStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }
}

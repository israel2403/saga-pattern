package com.huerta.orders.shared.mapper;

import com.huerta.orders.domain.model.OrderHistoryEntity;
import com.huerta.orders.shared.dto.OrderHistory;
import java.util.function.Function;

public interface OrderHistoryMapper {
    Function<OrderHistoryEntity, OrderHistory> fromOrderHistoryEntityToOrderHistory =
            orderHistoryEntity -> {
                return OrderHistory.builder()
                        .id(orderHistoryEntity.getId())
                        .orderId(orderHistoryEntity.getOrderId())
                        .status(orderHistoryEntity.getStatus())
                        .createdAt(orderHistoryEntity.getCreatedAt())
                        .build();
            };
}

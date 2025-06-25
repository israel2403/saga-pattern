package com.huerta.orders.application.usecase.orderhistory.createorderhistory;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.huerta.core.types.OrderStatus;
import com.huerta.orders.domain.model.OrderHistoryEntity;

@Component
public interface CreateOrderHistoryUseCase {

    public OrderHistoryEntity execute(UUID orderId, OrderStatus orderStatus);
}

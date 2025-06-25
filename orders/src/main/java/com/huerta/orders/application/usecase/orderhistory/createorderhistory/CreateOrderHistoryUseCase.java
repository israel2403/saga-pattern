package com.huerta.orders.application.usecase.orderhistory.createorderhistory;

import java.util.UUID;

import com.huerta.core.types.OrderStatus;
import com.huerta.orders.domain.model.OrderHistoryEntity;

public interface CreateOrderHistoryUseCase {

    public OrderHistoryEntity execute(UUID orderId, OrderStatus orderStatus);
}

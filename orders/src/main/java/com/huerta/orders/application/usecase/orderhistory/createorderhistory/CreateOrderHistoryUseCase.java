package com.huerta.orders.application.usecase.orderhistory.createorderhistory;

import com.huerta.core.types.OrderStatus;
import com.huerta.orders.domain.model.OrderHistoryEntity;
import java.util.UUID;

public interface CreateOrderHistoryUseCase {

    public OrderHistoryEntity execute(UUID orderId, OrderStatus orderStatus);
}

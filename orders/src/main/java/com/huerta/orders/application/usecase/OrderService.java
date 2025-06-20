package com.huerta.orders.application.usecase;

import com.huerta.core.dto.Order;

public interface OrderService {
    Order placeOrder(Order order);
}

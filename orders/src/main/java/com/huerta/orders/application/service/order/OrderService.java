package com.huerta.orders.application.service.order;

import com.huerta.core.dto.Order;

public interface OrderService {
    Order placeOrder(Order order);
}

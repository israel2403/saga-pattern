package com.huerta.orders.application.usecase.order.createorder;

import com.huerta.core.dto.Order;

public interface CreateOrderUseCase {

    /**
     * Executes the use case to create an order.
     *
     * @param order the order to be created
     * @return the created order
     */
    Order execute(Order order);
}

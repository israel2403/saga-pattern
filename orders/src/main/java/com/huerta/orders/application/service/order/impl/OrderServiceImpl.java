package com.huerta.orders.application.service.order.impl;

import com.huerta.core.dto.Order;
import com.huerta.orders.application.service.order.OrderService;
import com.huerta.orders.application.usecase.order.createorder.CreateOrderUseCase;
import com.huerta.orders.application.usecase.order.find.byorderid.FindOrderUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CreateOrderUseCase createOrderUseCase;
    private final FindOrderUseCase findOrderUseCase;

    @Override
    public Order placeOrder(Order order) {
        return createOrderUseCase.execute(order);
    }

    public Order findOrderById(final UUID orderId) {
        return this.findOrderUseCase.execute(orderId);
    }
}

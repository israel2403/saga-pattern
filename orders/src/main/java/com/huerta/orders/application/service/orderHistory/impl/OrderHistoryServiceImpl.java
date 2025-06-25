package com.huerta.orders.application.service.orderHistory.impl;

import java.util.List;
import java.util.UUID;

import com.huerta.core.types.OrderStatus;
import com.huerta.orders.application.service.orderHistory.OrderHistoryService;
import com.huerta.orders.application.usecase.orderhistory.createorderhistory.CreateOrderHistoryUseCase;
import com.huerta.orders.application.usecase.orderhistory.findby.orderId.FindOrdersHistoryByOrderIdUseCase;
import com.huerta.orders.domain.model.OrderHistoryEntity;
import com.huerta.orders.shared.dto.OrderHistory;
import com.huerta.orders.shared.mapper.OrderHistoryMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private final CreateOrderHistoryUseCase createOrderHistoryUseCase;

    private final FindOrdersHistoryByOrderIdUseCase findOrdersHistoryByOrderIdUseCase;

    @Override
    public OrderHistory create(UUID orderId, OrderStatus orderStatus) {
        // Create a new OrderHistory object and set its properties
        final OrderHistoryEntity orderHistoryEntity = this.createOrderHistoryUseCase.execute(orderId, orderStatus);
        return OrderHistoryMapper.fromOrderHistoryEntityToOrderHistory.apply(orderHistoryEntity);
    }

    @Override
    public List<OrderHistory> findByOrderId(UUID orderId) {
        // Retrieve the list of OrderHistory entries by orderId
        return this.findOrdersHistoryByOrderIdUseCase.execute(orderId).stream()
                .map(OrderHistoryMapper.fromOrderHistoryEntityToOrderHistory)
                .toList();
    }
}

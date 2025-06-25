package com.huerta.orders.application.service.orderHistory;

import java.util.List;
import java.util.UUID;

import com.huerta.core.types.OrderStatus;
import com.huerta.orders.domain.model.OrderHistoryEntity;
import com.huerta.orders.domain.repository.OrderHistoryPersistencePort;
import com.huerta.orders.shared.dto.OrderHistory;
import com.huerta.orders.shared.mapper.OrderHistoryMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryPersistencePort orderHistoryPersistencePort;

    @Override
    public OrderHistoryEntity create(UUID orderId, OrderStatus orderStatus) {
        // Create a new OrderHistory object and set its properties
        OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity(orderId, orderStatus);
        return this.orderHistoryPersistencePort.save(orderHistoryEntity);
    }

    @Override
    public List<OrderHistory> findByOrderId(UUID orderId) {
        // Retrieve the list of OrderHistory entries by orderId
        return this.orderHistoryPersistencePort.findAllById(orderId).stream()
                .map(OrderHistoryMapper.fromOrderHistoryEntityToOrderHistory)
                .toList();
    }
}

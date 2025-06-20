package com.huerta.orders.application.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.huerta.core.types.OrderStatus;
import com.huerta.orders.domain.repository.OrderHistoryPersistencePort;
import com.huerta.orders.shared.dto.OrderHistory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryPersistencePort orderHistoryPersistencePort;

    @Override
    public void add(UUID orderId, OrderStatus orderStatus) {
        // Create a new OrderHistory object and set its properties
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(orderId);
        orderHistory.setStatus(orderStatus);
        this.orderHistoryPersistencePort.save(orderHistory);
    }

    @Override
    public List<OrderHistory> findByOrderId(UUID orderId) {
        // Retrieve the list of OrderHistory entries by orderId
        List<OrderHistory> orderHistories = this.orderHistoryPersistencePort.findAllById(orderId);

        // Create a new list to hold the copied OrderHistory objects
        return orderHistories.stream()
                .map(orderHistory -> {
                    OrderHistory copiedOrderHistory = new OrderHistory();
                    BeanUtils.copyProperties(orderHistory, copiedOrderHistory);
                    return copiedOrderHistory;
                })
                .toList();
    }
}

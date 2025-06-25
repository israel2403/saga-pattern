package com.huerta.orders.application.usecase.orderhistory.findby.orderId.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.huerta.orders.application.usecase.orderhistory.findby.orderId.FindOrdersHistoryByOrderIdUseCase;
import com.huerta.orders.domain.model.OrderHistoryEntity;
import com.huerta.orders.domain.repository.OrderHistoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindOrdersHistoryByOrderIdUseCaseImpl implements FindOrdersHistoryByOrderIdUseCase {

    private final OrderHistoryPersistencePort orderHistoryPersistencePort;

    @Override
    public List<OrderHistoryEntity> execute(UUID orderId) {
        return orderHistoryPersistencePort.findAllById(orderId);
    }
}

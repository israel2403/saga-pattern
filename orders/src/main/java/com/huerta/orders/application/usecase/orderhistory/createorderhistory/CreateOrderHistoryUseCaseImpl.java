package com.huerta.orders.application.usecase.orderhistory.createorderhistory;

import com.huerta.core.types.OrderStatus;
import com.huerta.orders.domain.model.OrderHistoryEntity;
import com.huerta.orders.domain.repository.OrderHistoryPersistencePort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateOrderHistoryUseCaseImpl implements CreateOrderHistoryUseCase {

    private final OrderHistoryPersistencePort orderHistoryPersistencePort;

    @Override
    public OrderHistoryEntity execute(final UUID orderId, final OrderStatus orderStatus) {
        return orderHistoryPersistencePort.save(orderId, orderStatus);
    }
}

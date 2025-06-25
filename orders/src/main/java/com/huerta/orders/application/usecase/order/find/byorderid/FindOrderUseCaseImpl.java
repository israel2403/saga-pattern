package com.huerta.orders.application.usecase.order.find.byorderid;

import com.huerta.core.dto.Order;
import com.huerta.orders.domain.repository.OrderPersistencePort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindOrderUseCaseImpl implements FindOrderUseCase {

    private final OrderPersistencePort orderPersistencePort;

    @Override
    public Order execute(UUID orderId) {
        return this.orderPersistencePort
                .findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
    }
}

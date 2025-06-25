package com.huerta.orders.application.usecase.order.createorder;

import com.huerta.core.dto.Order;
import com.huerta.core.dto.events.OrderCreatedEvent;
import com.huerta.orders.domain.repository.OrderPersistencePort;
import com.huerta.orders.infrastructure.messaging.KafkaOrderEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderPersistencePort orderPersistencePort;
    private final KafkaOrderEventPublisher eventPublisher;

    @Override
    public Order execute(Order order) {
        orderPersistencePort.save(order);
        OrderCreatedEvent event =
                new OrderCreatedEvent(
                        order.getOrderId(),
                        order.getCustomerId(),
                        order.getProductId(),
                        order.getProductQuantity());

        eventPublisher.publishOrderCreated(event);
        return order;
    }
}

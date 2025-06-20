package com.huerta.orders.application.usecase;

import com.huerta.core.dto.Order;
import com.huerta.core.dto.events.OrderCreatedEvent;
import com.huerta.core.types.OrderStatus;
import com.huerta.orders.domain.repository.OrderPersistencePort;
import com.huerta.orders.infrastructure.messaging.KafkaOrderEventPublisher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderPersistencePort orderPersistencePort;
    private final KafkaOrderEventPublisher kafkaOrderEventPublisher;

    @Override
    public Order placeOrder(Order order) {
        Order newOrder = new Order(
                null,
                order.getCustomerId(),
                order.getProductId(),
                order.getProductQuantity(),
                OrderStatus.CREATED);
        newOrder = this.orderPersistencePort.save(newOrder);

        final OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(
                newOrder.getOrderId(),
                newOrder.getCustomerId(),
                newOrder.getProductId(),
                newOrder.getProductQuantity());

        this.kafkaOrderEventPublisher.publishOrderCreated(orderCreatedEvent);
        return newOrder;
    }
}

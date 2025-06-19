package com.huerta.orders.service;

import com.huerta.core.dto.Order;
import com.huerta.core.dto.events.OrderCreatedEvent;
import com.huerta.core.types.OrderStatus;
import com.huerta.orders.dao.jpa.entity.OrderEntity;
import com.huerta.orders.dao.jpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${orders.events.topic.name}")
    private final String ordersEventsTopicName;

    @Override
    public Order placeOrder(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerId(order.getCustomerId());
        entity.setProductId(order.getProductId());
        entity.setProductQuantity(order.getProductQuantity());
        entity.setStatus(OrderStatus.CREATED);
        orderRepository.save(entity);

        final OrderCreatedEvent orderCreatedEvent =
                new OrderCreatedEvent(
                        entity.getId(),
                        entity.getCustomerId(),
                        entity.getProductId(),
                        entity.getProductQuantity());

        kafkaTemplate.send("ordersEventsTopicName", orderCreatedEvent);
        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getProductId(),
                entity.getProductQuantity(),
                entity.getStatus());
    }
}

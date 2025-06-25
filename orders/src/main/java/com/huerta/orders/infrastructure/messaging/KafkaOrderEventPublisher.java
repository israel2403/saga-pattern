package com.huerta.orders.infrastructure.messaging;

import com.huerta.core.dto.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaOrderEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${orders.events.topic.name}")
    private String ordersEventsTopicName;

    public void publishOrderCreated(final OrderCreatedEvent orderCreatedEvent) {
        if (orderCreatedEvent != null) {
            kafkaTemplate.send(ordersEventsTopicName, orderCreatedEvent);
        } else {
            throw new IllegalArgumentException("OrderCreatedEvent cannot be null");
        }
    }
}

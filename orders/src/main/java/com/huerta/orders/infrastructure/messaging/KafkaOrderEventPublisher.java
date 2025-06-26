package com.huerta.orders.infrastructure.messaging;

import com.huerta.core.dto.events.OrderCreatedEvent;
import com.huerta.orders.infrastructure.exception.EventPublishingException;
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
        if (orderCreatedEvent == null) {
            throw new IllegalArgumentException("OrderCreatedEvent cannot be null");
        }

        try {
            kafkaTemplate.send(ordersEventsTopicName, orderCreatedEvent);
        } catch (Exception e) {
            throw new EventPublishingException(
                    "Failed to publish OrderCreatedEvent to topic:" + ordersEventsTopicName, e);
        }
    }
}

package com.huerta.orders.infrastructure.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.huerta.core.dto.events.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;

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

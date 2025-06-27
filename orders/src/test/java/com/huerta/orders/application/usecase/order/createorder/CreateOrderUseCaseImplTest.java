package com.huerta.orders.application.usecase.order.createorder;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huerta.core.dto.Order;
import com.huerta.orders.domain.repository.OrderPersistencePort;
import com.huerta.orders.infrastructure.messaging.KafkaOrderEventPublisher;

public class CreateOrderUseCaseImplTest {

  @Mock
  private OrderPersistencePort orderPersistencePort;

  @Mock
  private KafkaOrderEventPublisher eventPublisher;

  @InjectMocks
  private CreateOrderUseCaseImpl createOrderUseCaseImpl;

  private Order sampleOrder;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    String jsonPath = "src/test/resources/json/appliation/usecase/order/createorder/sampleOrder.json";
    String orderJson = new String(Files.readAllBytes(Paths.get(jsonPath)));
    ObjectMapper objectMapper = new ObjectMapper();
    sampleOrder = objectMapper.readValue(orderJson, Order.class);
  }

  @Test
  void shouldCreateOrderAndPublishEvent() {
    // Act
    Order result = createOrderUseCaseImpl.execute(sampleOrder);

    // Assert
    assertEquals(sampleOrder, result);

  }
}

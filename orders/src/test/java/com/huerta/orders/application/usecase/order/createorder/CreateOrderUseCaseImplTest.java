package com.huerta.orders.application.usecase.order.createorder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huerta.core.dto.Order;
import com.huerta.orders.domain.repository.OrderPersistencePort;
import com.huerta.orders.infrastructure.messaging.KafkaOrderEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateOrderUseCaseImplTest {

    @Mock private OrderPersistencePort orderPersistencePort;

    @Mock private KafkaOrderEventPublisher eventPublisher;

    @InjectMocks private CreateOrderUseCaseImpl createOrderUseCaseImpl;

    private Order validOrder;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        this.validOrder =
                objectMapper.readValue(
                        getClass()
                                .getClassLoader()
                                .getResourceAsStream(
                                        "json/application/usecase/order/createorder/valid-order.json"),
                        Order.class);
    }

    @Test
    void shouldCreateOrderAndPublishEvent() {
        // Act
        Order result = createOrderUseCaseImpl.execute(validOrder);

        // Assert
        assertEquals(validOrder, result);
        verify(orderPersistencePort, times(1)).save(validOrder);
    }
}

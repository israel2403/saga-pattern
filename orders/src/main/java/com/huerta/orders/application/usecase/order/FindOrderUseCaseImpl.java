package com.huerta.orders.application.usecase.order;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.huerta.core.dto.Order;
import com.huerta.orders.domain.repository.OrderPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindOrderUseCaseImpl implements FindOrderUseCase {

  private final OrderPersistencePort orderPersistencePort;

  @Override
  public Order execute(UUID orderId) {
    return this.orderPersistencePort.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
  }

}

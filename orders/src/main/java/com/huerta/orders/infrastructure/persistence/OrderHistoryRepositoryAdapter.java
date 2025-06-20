package com.huerta.orders.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.huerta.orders.domain.model.OrderHistoryEntity;
import com.huerta.orders.domain.repository.OrderHistoryPersistencePort;
import com.huerta.orders.shared.dto.OrderHistory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderHistoryRepositoryAdapter implements OrderHistoryPersistencePort {
  private final OrderHistoryEntityRepository orderHistoryEntityRepository;

  @Override
  public OrderHistory save(OrderHistory orderHistory) {
    final OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity(
        orderHistory.getOrderId(),
        orderHistory.getStatus());
    this.orderHistoryEntityRepository.save(orderHistoryEntity);
    return orderHistory;
  }

  @Override
  public List<OrderHistory> findAllById(UUID orderId) {

    return this.orderHistoryEntityRepository.findByOrderId(orderId).stream()
        .map(entity -> {
          OrderHistory orderHistory = new OrderHistory();
          orderHistory.setOrderId(entity.getOrderId());
          orderHistory.setStatus(entity.getStatus());
          return orderHistory;
        })
        .toList();
  }

}

package com.huerta.orders.infrastructure.persistence;

import com.huerta.core.dto.Order;
import com.huerta.orders.domain.model.OrderEntity;
import com.huerta.orders.domain.repository.OrderPersistencePort;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderPersistencePort {

    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        final OrderEntity orderEntity =
                new OrderEntity(
                        order.getCustomerId(),
                        order.getProductId(),
                        order.getProductQuantity(),
                        order.getStatus());
        this.orderRepository.save(orderEntity);
        order.setOrderId(orderEntity.getId());
        return order;
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        return orderRepository
                .findById(orderId)
                .map(
                        orderEntity ->
                                new Order(
                                        orderEntity.getId(),
                                        orderEntity.getCustomerId(),
                                        orderEntity.getProductId(),
                                        orderEntity.getProductQuantity(),
                                        orderEntity.getStatus()));
    }
}

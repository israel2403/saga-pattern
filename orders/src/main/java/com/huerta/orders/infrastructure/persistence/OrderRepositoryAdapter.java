package com.huerta.orders.infrastructure.persistence;

import com.huerta.core.dto.Order;
import com.huerta.orders.domain.model.OrderEntity;
import com.huerta.orders.domain.repository.OrderPersistencePort;
import com.huerta.orders.infrastructure.exception.OrderPersistenceException;
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
        try {
            final OrderEntity orderEntity =
                    new OrderEntity(
                            order.getCustomerId(),
                            order.getProductId(),
                            order.getProductQuantity(),
                            order.getStatus());
            this.orderRepository.save(orderEntity);
            order.setOrderId(orderEntity.getId());
            return order;
        } catch (Exception e) {
            throw new OrderPersistenceException("Error saving order", e);
        }
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        try {

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
        } catch (Exception e) {
            throw new OrderPersistenceException("Error finding order by ID", e);
        }
    }
}

package com.huerta.orders.infrastructure.persistence;

import com.huerta.orders.domain.model.OrderHistoryEntity;
import com.huerta.orders.domain.repository.OrderHistoryPersistencePort;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderHistoryRepositoryAdapter implements OrderHistoryPersistencePort {
    private final OrderHistoryEntityRepository orderHistoryEntityRepository;

    @Override
    public OrderHistoryEntity save(OrderHistoryEntity orderHistoryEntity) {
        this.orderHistoryEntityRepository.save(orderHistoryEntity);
        return orderHistoryEntity;
    }

    @Override
    public List<OrderHistoryEntity> findAllById(UUID orderId) {

        return this.orderHistoryEntityRepository.findByOrderId(orderId);
    }
}

package com.huerta.orders.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huerta.orders.domain.model.OrderHistoryEntity;

public interface OrderHistoryEntityRepository extends JpaRepository<OrderHistoryEntity, UUID> {

    /**
     * Find the latest order history entry for a given order ID.
     *
     * @param orderId the ID of the order
     * @return a list of OrderHistoryEntity objects associated with the given order
     *         ID
     */
    List<OrderHistoryEntity> findByOrderId(UUID orderId);
}

package com.huerta.orders.domain.repository;

import com.huerta.orders.domain.model.OrderHistoryEntity;
import java.util.List;
import java.util.UUID;

public interface OrderHistoryPersistencePort {
    /**
     * Saves an order history entry.
     *
     * @param orderHistoryEntity the order history entry to save
     * @return the saved order history entry
     */
    OrderHistoryEntity save(OrderHistoryEntity orderHistoryEntity);

    /**
     * Finds all order history entries by order ID.
     *
     * @param orderId the ID of the order to find history for
     * @return a list of order history entries associated with the given order ID
     */
    List<OrderHistoryEntity> findAllById(UUID orderId);
}

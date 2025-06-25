package com.huerta.orders.domain.repository;

import com.huerta.core.dto.Order;
import java.util.Optional;
import java.util.UUID;

public interface OrderPersistencePort {

    /**
     * Saves the given order to the persistence layer.
     *
     * @param order the order to save
     * @return the saved order
     */
    Order save(Order order);

    /**
     * Finds an order by its ID.
     *
     * @param orderId the ID of the order to find
     * @return an Optional containing the found order, or empty if no order was found
     */
    Optional<Order> findById(UUID orderId);
}

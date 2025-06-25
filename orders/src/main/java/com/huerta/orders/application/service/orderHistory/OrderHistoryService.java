package com.huerta.orders.application.service.orderHistory;

import com.huerta.core.types.OrderStatus;
import com.huerta.orders.shared.dto.OrderHistory;
import java.util.List;
import java.util.UUID;

public interface OrderHistoryService {

    /**
     * Adds a new order history entry for the given order ID and status.
     *
     * @param orderId the ID of the order
     * @param orderStatus the status of the order
     * @return the created OrderHistoryEntity
     */
    OrderHistory create(UUID orderId, OrderStatus orderStatus);

    /**
     * Retrieves the latest order history entry for a given order ID.
     *
     * @param orderId the ID of the order
     * @return a list of OrderHistory objects associated with the given order ID
     */
    List<OrderHistory> findByOrderId(UUID orderId);
}

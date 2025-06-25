package com.huerta.orders.application.usecase.orderhistory.findby.orderId;

import com.huerta.orders.domain.model.OrderHistoryEntity;
import java.util.List;
import java.util.UUID;

public interface FindOrdersHistoryByOrderIdUseCase {

    /**
     * Retrieves the latest order history entry for a given order ID.
     *
     * @param orderId the ID of the order
     * @return a list of OrderHistoryEntity objects associated with the given order ID
     */
    List<OrderHistoryEntity> execute(UUID orderId);
}

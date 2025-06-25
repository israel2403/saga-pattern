package com.huerta.orders.application.usecase.order.find.byorderid;

import com.huerta.core.dto.Order;
import java.util.UUID;

public interface FindOrderUseCase {

    /**
     * Finds an order by its ID.
     *
     * @param orderId the ID of the order to find
     * @return the found Order, or null if no order with the given ID exists
     */
    Order execute(UUID orderId);
}

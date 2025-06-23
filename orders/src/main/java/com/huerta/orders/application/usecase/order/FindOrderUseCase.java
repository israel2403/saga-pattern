package com.huerta.orders.application.usecase.order;

import java.util.UUID;

import com.huerta.core.dto.Order;

public interface FindOrderUseCase {

  /**
   * Finds an order by its ID.
   *
   * @param orderId the ID of the order to find
   * @return the found Order, or null if no order with the given ID exists
   */
  Order execute(UUID orderId);
}

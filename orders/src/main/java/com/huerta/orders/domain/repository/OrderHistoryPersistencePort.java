package com.huerta.orders.domain.repository;

import java.util.List;
import java.util.UUID;

import com.huerta.orders.shared.dto.OrderHistory;

public interface OrderHistoryPersistencePort {
  /**
   * Saves an order history entry.
   *
   * @param orderHistory the order history entry to save
   * @return the saved order history entry
   */
  OrderHistory save(OrderHistory orderHistory);

  /**
   * Finds all order history entries by order ID.
   *
   * @param orderId the ID of the order to find history for
   * @return a list of order history entries associated with the given order ID
   */
  List<OrderHistory> findAllById(UUID orderId);
}

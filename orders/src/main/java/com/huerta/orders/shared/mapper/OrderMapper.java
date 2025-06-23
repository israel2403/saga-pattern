package com.huerta.orders.shared.mapper;

import org.springframework.stereotype.Component;

import com.huerta.core.dto.Order;
import com.huerta.orders.shared.dto.CreateOrderRequest;
import com.huerta.orders.shared.dto.CreateOrderResponse;
import com.huerta.orders.shared.dto.OrderHistory;
import com.huerta.orders.shared.dto.OrderHistoryResponse;

@Component
public class OrderMapper {
  public Order toDomain(CreateOrderRequest createOrderRequest) {
    Order order = new Order();
    order.setCustomerId(createOrderRequest.getCustomerId());
    order.setProductId(createOrderRequest.getProductId());
    order.setProductQuantity(createOrderRequest.getProductQuantity());
    return order;
  }

  public CreateOrderResponse toCreateResponse(final Order order) {
    return CreateOrderResponse.builder()
        .orderId(order.getOrderId())
        .customerId(order.getCustomerId())
        .productId(order.getProductId())
        .productQuantity(order.getProductQuantity())
        .status(order.getStatus())
        .build();
  }

  public OrderHistoryResponse fromOrderHistoryToHistoryResponse(final OrderHistory orderHistory) {
    return OrderHistoryResponse.builder()
        .id(orderHistory.getId())
        .orderId(orderHistory.getOrderId())
        .createdAt(orderHistory.getCreatedAt())
        .status(orderHistory.getStatus())
        .build();
  }
}

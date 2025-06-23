package com.huerta.orders.infrastructure.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.huerta.core.dto.Order;
import com.huerta.orders.application.usecase.order.CreateOrderUseCase;
import com.huerta.orders.application.usecase.orderhistory.OrderHistoryService;
import com.huerta.orders.shared.dto.CreateOrderRequest;
import com.huerta.orders.shared.dto.CreateOrderResponse;
import com.huerta.orders.shared.dto.OrderHistoryResponse;
import com.huerta.orders.shared.mapper.OrderMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final CreateOrderUseCase createOrderUseCase;
    private final OrderHistoryService orderHistoryService;
    private final OrderMapper orderMapper;

    @PostMapping
    public CreateOrderResponse placeOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        final Order orderCreated = createOrderUseCase.execute(orderMapper.toDomain(createOrderRequest));
        final CreateOrderResponse response = orderMapper.toCreateResponse(orderCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(response).getBody();
    }

    @GetMapping("/{orderId}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderHistoryResponse> getOrderHistory(@PathVariable UUID orderId) {
        return orderHistoryService.findByOrderId(orderId)
                .stream().map(orderMapper::fromOrderHistoryToHistoryResponse)
                .toList();
    }
}

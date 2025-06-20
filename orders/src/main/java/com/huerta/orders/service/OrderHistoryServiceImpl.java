package com.huerta.orders.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.huerta.core.types.OrderStatus;
import com.huerta.orders.application.usecase.OrderHistoryService;
import com.huerta.orders.domain.model.OrderHistoryEntity;
import com.huerta.orders.infrastructure.persistence.OrderHistoryRepository;
import com.huerta.orders.shared.dto.OrderHistory;

public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    public OrderHistoryServiceImpl(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public void add(UUID orderId, OrderStatus orderStatus) {
        OrderHistoryEntity entity = new OrderHistoryEntity();
        entity.setOrderId(orderId);
        entity.setStatus(orderStatus);
        entity.setCreatedAt(new Timestamp(new Date().getTime()));
        orderHistoryRepository.save(entity);
    }

    @Override
    public List<OrderHistory> findByOrderId(UUID orderId) {
        var entities = orderHistoryRepository.findByOrderId(orderId);
        return entities.stream()
                .map(
                        entity -> {
                            OrderHistory orderHistory = new OrderHistory();
                            BeanUtils.copyProperties(entity, orderHistory);
                            return orderHistory;
                        })
                .toList();
    }
}

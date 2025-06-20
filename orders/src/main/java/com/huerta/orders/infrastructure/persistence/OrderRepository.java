package com.huerta.orders.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huerta.orders.domain.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}

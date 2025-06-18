package com.huerta.orders.dao.jpa.repository;

import com.huerta.orders.dao.jpa.entity.OrderEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {}

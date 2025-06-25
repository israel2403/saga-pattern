package com.huerta.orders.infrastructure.persistence;

import com.huerta.orders.domain.model.OrderEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {}

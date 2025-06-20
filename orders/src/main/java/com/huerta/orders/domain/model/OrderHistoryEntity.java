package com.huerta.orders.domain.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.huerta.core.types.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "orders_history")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "created_at")
    private Timestamp createdAt;
}

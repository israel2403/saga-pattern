package com.huerta.orders.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderRequest {
    @NotNull private UUID customerId;
    @NotNull private UUID productId;
    @NotNull @Positive private Integer productQuantity;
}

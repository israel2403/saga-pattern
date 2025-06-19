package com.huerta.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductCreationRequest {
    @NotBlank private String name;
    @NotNull @Positive private BigDecimal price;
    @Positive private Integer quantity;
}

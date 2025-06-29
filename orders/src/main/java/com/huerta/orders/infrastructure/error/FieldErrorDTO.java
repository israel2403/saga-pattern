package com.huerta.orders.infrastructure.error;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FieldErrorDTO {
    private final String field;

    private final String message;

    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }
}

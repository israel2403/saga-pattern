package com.huerta.orders.infrastructure.error;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ErrorResponseDTO {

    private final String apiPath;

    private final HttpStatus errorCode;

    private final String errorMessage;

    private final LocalDateTime errorTime;

    private final List<FieldErrorDTO> errors;

    public ErrorResponseDTO(
            String apiPath,
            HttpStatus errorCode,
            String errorMessage,
            LocalDateTime errorTime,
            List<FieldErrorDTO> errors) {

        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTime = errorTime != null ? LocalDateTime.from(errorTime) : null;
        this.errors = errors != null ? List.copyOf(errors) : Collections.emptyList();
    }

    public String getApiPath() {
        return apiPath;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getErrorTime() {
        return errorTime != null ? LocalDateTime.from(errorTime) : null;
    }

    public List<FieldErrorDTO> getErrors() {
        return List.copyOf(errors);
    }
}

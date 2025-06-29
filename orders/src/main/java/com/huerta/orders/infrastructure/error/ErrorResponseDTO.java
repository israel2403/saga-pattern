package com.huerta.orders.infrastructure.error;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;

public final class ErrorResponseDTO {

    private final String apiPath;
    private final HttpStatus errorCode;
    private final String errorMessage;
    private final LocalDateTime errorTime;
    private final List<FieldErrorDTO> errors;

    private ErrorResponseDTO(Builder builder) {
        this.apiPath = builder.apiPath;
        this.errorCode = builder.errorCode;
        this.errorMessage = builder.errorMessage;
        this.errorTime = builder.errorTime != null ? LocalDateTime.from(builder.errorTime) : null;
        this.errors =
                builder.errors != null ? List.copyOf(builder.errors) : Collections.emptyList();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String apiPath;
        private HttpStatus errorCode;
        private String errorMessage;
        private LocalDateTime errorTime;
        private List<FieldErrorDTO> errors;

        public Builder apiPath(String apiPath) {
            this.apiPath = apiPath;
            return this;
        }

        public Builder errorCode(HttpStatus errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder errorTime(LocalDateTime errorTime) {
            this.errorTime = errorTime;
            return this;
        }

        public Builder errors(List<FieldErrorDTO> errors) {
            this.errors = errors != null ? List.copyOf(errors) : Collections.emptyList();
            return this;
        }

        public ErrorResponseDTO build() {
            return new ErrorResponseDTO(this);
        }
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

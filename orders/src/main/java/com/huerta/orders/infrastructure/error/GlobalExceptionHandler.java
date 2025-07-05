package com.huerta.orders.infrastructure.error;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.huerta.orders.infrastructure.exception.EventPublishingException;
import com.huerta.orders.infrastructure.exception.OrderPersistenceException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(OrderPersistenceException.class)
        public ResponseEntity<ErrorResponseDTO> handlePersistenceException(
                        OrderPersistenceException ex, HttpServletRequest request) {
                ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                                .apiPath(request.getRequestURI())
                                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                                .errorMessage(ex.getMessage())
                                .errorTime(LocalDateTime.now())
                                .errors(List.of())
                                .build();

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

        @ExceptionHandler(EventPublishingException.class)
        public ResponseEntity<ErrorResponseDTO> handlePublishingException(
                        EventPublishingException ex, HttpServletRequest request) {

                ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                                .apiPath(request.getRequestURI())
                                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                                .errorMessage(ex.getMessage())
                                .errorTime(LocalDateTime.now())
                                .errors(List.of())
                                .build();

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponseDTO> handleGenericException(
                        Exception ex, HttpServletRequest request) {
                ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                                .apiPath(request.getRequestURI())
                                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                                .errorMessage("An unexpected error occurred: " + ex.getMessage())
                                .errorTime(LocalDateTime.now())
                                .errors(List.of())
                                .build();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDTO);
        }
}

package com.huerta.products.service;

import com.huerta.core.dto.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> findAll();

    Product reserve(Product desiredProduct, UUID orderId);

    void cancelReservation(Product productToCancel, UUID orderId);

    Product save(Product product);
}

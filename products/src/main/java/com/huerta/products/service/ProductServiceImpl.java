package com.huerta.products.service;

import com.huerta.core.dto.Product;
import com.huerta.core.exceptions.ProductInsufficientQuantityException;
import com.huerta.products.dao.jpa.entity.ProductEntity;
import com.huerta.products.dao.jpa.repository.ProductRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product reserve(Product desiredProduct, UUID orderId) {
        ProductEntity productEntity =
                productRepository.findById(desiredProduct.getId()).orElseThrow();
        if (desiredProduct.getQuantity() > productEntity.getQuantity()) {
            throw new ProductInsufficientQuantityException(productEntity.getId(), orderId);
        }

        productEntity.setQuantity(productEntity.getQuantity() - desiredProduct.getQuantity());
        productRepository.save(productEntity);

        var reservedProduct = new Product();
        BeanUtils.copyProperties(productEntity, reservedProduct);
        reservedProduct.setQuantity(desiredProduct.getQuantity());
        return reservedProduct;
    }

    @Override
    public void cancelReservation(Product productToCancel, UUID orderId) {
        ProductEntity productEntity =
                productRepository.findById(productToCancel.getId()).orElseThrow();
        productEntity.setQuantity(productEntity.getQuantity() + productToCancel.getQuantity());
        productRepository.save(productEntity);
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQuantity());
        productRepository.save(productEntity);

        return new Product(
                productEntity.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity());
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .map(
                        entity ->
                                new Product(
                                        entity.getId(),
                                        entity.getName(),
                                        entity.getPrice(),
                                        entity.getQuantity()))
                .collect(Collectors.toList());
    }
}

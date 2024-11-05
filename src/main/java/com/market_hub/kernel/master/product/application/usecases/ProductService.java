package com.market_hub.kernel.master.product.application.usecases;

import com.market_hub.kernel.master.global.domain.dtos.product.ProductRequestDto;
import com.market_hub.kernel.master.global.domain.dtos.product.ProductResponseDto;
import com.market_hub.kernel.master.product.infraestructure.model.Product;

import java.util.List;

public interface ProductService {
    ProductResponseDto search(Long id);
    ProductResponseDto create(ProductRequestDto object) throws Exception;
    ProductResponseDto update(ProductRequestDto object);
    void activeDelete(Long id);
    ProductResponseDto convertToDto(Product product);
    List<ProductResponseDto> findProductBySku(String sku);
}

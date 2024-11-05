package com.market_hub.kernel.master.product.domain.currentInterfaces;

import com.market_hub.kernel.master.product.infraestructure.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> allProductOfUser(String identification, Boolean isChanged);

    List<Product> findBySku(String sku);

    void activeDeleted(Long id);
}

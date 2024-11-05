package com.market_hub.kernel.master.product.application.usecases;
import com.market_hub.kernel.master.product.infraestructure.model.Product;


import java.util.Optional;

public interface ProductBasicOperations {

    Optional<Product> update(Product product);

    Optional<Product> create(Product product);

    Optional<Product> findUserById(Long id);



}

package com.market_hub.kernel.master.product.application.implementations;

import com.market_hub.kernel.master.global.domain.dtos.product.ProductRequestDto;
import com.market_hub.kernel.master.global.domain.dtos.product.ProductResponseDto;
import com.market_hub.kernel.master.global.domain.ports.DaoCrudPort;
import com.market_hub.kernel.master.global.infraestructure.advicers.exceptions.ResourceNotFound;
import com.market_hub.kernel.master.product.application.usecases.ProductBasicOperations;
import com.market_hub.kernel.master.product.application.usecases.ProductService;
import com.market_hub.kernel.master.product.domain.currentInterfaces.ProductDao;
import com.market_hub.kernel.master.product.domain.logic.FileUpload;
import com.market_hub.kernel.master.product.infraestructure.model.Product;
import com.market_hub.kernel.master.user.application.usecases.UserServiceBasicOperations;
import com.market_hub.kernel.master.user.infraestructure.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService, ProductBasicOperations {

    private final ProductDao productDaoPort;

    private final DaoCrudPort<Product> productDaoCrudPort;

    private final FileUpload fileUpload;

    private final UserServiceBasicOperations userServiceBasicOperations;

    public ProductServiceImpl(ProductDao productDaoPort,
                              DaoCrudPort<Product> productDaoCrudPort,
                              FileUpload fileUpload,
                              UserServiceBasicOperations userServiceBasicOperations){
        this.productDaoPort = productDaoPort;
        this.productDaoCrudPort = productDaoCrudPort;
        this.fileUpload = fileUpload;
        this.userServiceBasicOperations = userServiceBasicOperations;
    }



    @Override
    public ProductResponseDto search(Long id) {
        return findUserById(id)
                .map(this::convertToDto)
                .orElseThrow();
    }

    @Override
    public ProductResponseDto create(ProductRequestDto object) throws Exception {
        String email = object.tokenInfo().email();
        Product product = new Product();
        User user = userServiceBasicOperations.findUserByEmail(email).orElseThrow(()-> new ResourceNotFound("User with this email not found"));

        String pathImg = fileUpload.uploadImg(object.multipartFile(),object.newFileName(),user);
        product.setName(object.name());
        product.setCategory(object.category());
        product.setDescription(object.description());
        product.setPrice((double) object.price());
        product.setCondition(object.condition());
        product.setImgUrl(pathImg);
        product.setDeleted(false);
        product.setIsChanged(false);
        product.setUser(user);

        return create(product)
                .map(this::convertToDto)
                .orElseThrow();

    }

    @Override
    public ProductResponseDto update(ProductRequestDto object) {
        Product product = new Product();
        product.setName(object.name());
        product.setPrice(Double.valueOf(object.price()));
        product.setDescription(object.description());
        product.setCategory(object.category());
        product.setCondition(object.condition());

        return update(product)
                .map(this::convertToDto)
                .orElseThrow();
    }



    @Override
    public Optional<Product> create(Product product) {
        return productDaoCrudPort.create(product);
    }

    @Override
    public void activeDelete(Long id) {
        productDaoPort.activeDeleted(id);
    }

    @Override
    public Optional<Product> update(Product product) {
        return productDaoCrudPort.update(product);
    }

    @Override
    public Optional<Product> findUserById(Long id) {
        return productDaoCrudPort.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findProductBySku(String sku) {
        return productDaoPort.findBySku(sku).stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto convertToDto(Product product) {
        return new ProductResponseDto(product.getId(),
                product.getCategory(),
                product.getPrice(),
                product.getCondition(),
                product.getName(),
                product.getImgUrl(),
                product.getDescription());
    }


}

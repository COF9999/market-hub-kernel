package com.market_hub.kernel.master.product.infraestructure.rest.controllers;

import com.market_hub.kernel.master.global.domain.dtos.product.ProductRequestDto;
import com.market_hub.kernel.master.global.domain.dtos.product.ProductResponseDto;
import com.market_hub.kernel.master.global.domain.dtos.token.TokenDto;
import com.market_hub.kernel.master.global.domain.dtos.token.TokenInfo;
import com.market_hub.kernel.master.product.application.usecases.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductControllerRest {

    private final ProductService productService;

    public ProductControllerRest(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id){
        return productService.search(id);
    }

    @PostMapping("/")
    public ProductResponseDto createProduct(
            @RequestParam("file") MultipartFile file,
            @RequestParam("newFileName") String newFileName,
            @RequestParam("category") String category,
            @RequestParam("price") Float price,
            @RequestParam("condition") Byte condition,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("tokenInfo") TokenInfo tokenInfo

    ) throws Exception {
        ProductRequestDto productDto = new ProductRequestDto(
                null,
                category,
                price,
                condition,
                name,
                null,
                file,
                newFileName,
                description,
                tokenInfo
        );
        System.out.println("ENTRO KERNEL");
        return null;
       // return productService.create(productDto);
    }

    @PutMapping("/")
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productDto){
        return productService.update(productDto);
    }

    @DeleteMapping("/active-delete-product/{id}")
    public void activeDeleteProduct(@PathVariable Long id){
        productService.activeDelete(id);
    }

    @PostMapping("/mock")
    public String mockProduct(@RequestBody TokenInfo tokenInfo){
        System.out.println("entro");
        return "Entro"+tokenInfo.email()+" Super!!!";
    }


}

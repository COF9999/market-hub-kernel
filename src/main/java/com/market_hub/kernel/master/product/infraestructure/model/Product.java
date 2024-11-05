package com.market_hub.kernel.master.product.infraestructure.model;

import com.market_hub.kernel.master.user.infraestructure.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private Double price;
    private String name;
    @Column(name = "condition_product")
    private Byte condition;
    private String imgUrl;
    private String description;
    private String sku;
    private Boolean deleted;

    @Column(name = "is_changed")
    private Boolean isChanged;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}

package com.market_hub.kernel.master.publication.infraestructure.model;

import com.market_hub.kernel.master.product.infraestructure.model.Product;
import com.market_hub.kernel.master.user.infraestructure.model.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date date;
    private Byte status;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User users;
}
package com.market_hub.kernel.master.role.infraestructure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.market_hub.kernel.master.user.infraestructure.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String name;


    @JsonIgnoreProperties({"roles",
            "handler",
            "hibernateLazyInitializer"
    })
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();


    public Role(){

    }
    public Role(String name) {
        this.name = name;
    }
}
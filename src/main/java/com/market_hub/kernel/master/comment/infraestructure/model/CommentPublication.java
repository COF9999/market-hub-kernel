package com.market_hub.kernel.master.comment.infraestructure.model;

import com.market_hub.kernel.master.publication.infraestructure.model.Publication;
import com.market_hub.kernel.master.user.infraestructure.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments_publication")
public class CommentPublication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commnetary;

    @ManyToOne
    @JoinColumn(name = "id_publication")
    private Publication publications;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User users;
}
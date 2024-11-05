package com.market_hub.kernel.master.publication.domain.currentInterfaces;

import com.market_hub.kernel.master.product.infraestructure.model.Product;
import com.market_hub.kernel.master.publication.infraestructure.model.Publication;

import java.util.List;
import java.util.Optional;

public interface PublicationDao {
    List<Product> searchProductInPublication(Long idProduct);

    List<Publication> searchAllPublicationsUser(String identification);

    Optional<Publication> searchPublicationById(Long id);

}

package com.market_hub.kernel.master.offer.domain.currentInterfaces;

import com.market_hub.kernel.master.user.infraestructure.model.User;
import java.util.List;

public interface OfferDao {
    List<User> findUserInOffer(Long id, String identificacion);

    /*
    List<Offers> allOffersByPublicationId(Long id,Byte state);

    List<Offers> productInOffers(Long idProduct,Byte state);

    List<Offers> searchOffersOfUser(Long idUser);

     */

}

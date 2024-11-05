package com.market_hub.kernel.master.user.domain.ports.currentInterfaces;

import com.market_hub.kernel.master.user.infraestructure.model.User;

import java.util.Optional;

public interface UserDaoPort {
    Optional<Object> findByEmail(String identification);

    void activeDeleted(Long id);
}

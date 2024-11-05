package com.market_hub.kernel.master.user.application.usecases;

import com.market_hub.kernel.master.user.infraestructure.model.User;

import java.util.Optional;

public interface UserServiceBasicOperations {
    Optional<User> findUserByEmail(String email);

    User update(User user);

    User create(User user);

    Optional<User> findUserById(Long id);
}

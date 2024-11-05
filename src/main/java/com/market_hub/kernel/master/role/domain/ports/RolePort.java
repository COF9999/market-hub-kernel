package com.market_hub.kernel.master.role.domain.ports;

import com.market_hub.kernel.master.role.infraestructure.model.Role;

import java.util.Optional;

public interface RolePort {
    Optional<Object> findRoleByName(String name);
}

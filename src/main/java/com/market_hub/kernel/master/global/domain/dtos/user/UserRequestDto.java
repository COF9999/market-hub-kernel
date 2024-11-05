package com.market_hub.kernel.master.global.domain.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.market_hub.kernel.master.role.infraestructure.model.Role;

import java.util.List;

public record UserRequestDto(
                             Long id,
                             String username,
                             String identification,
                             @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) String password,
                             String name,
                             String email,
                             Boolean isAdmin,
                             List<Role> roles) {
}

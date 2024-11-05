package com.market_hub.kernel.master.user.application.usecases;

import com.market_hub.kernel.master.global.domain.dtos.user.UserRequestDto;
import com.market_hub.kernel.master.global.domain.dtos.user.UserResponseDto;
import com.market_hub.kernel.master.user.infraestructure.model.User;

public interface UserService {
    UserResponseDto search(Long id);
    UserResponseDto create(UserRequestDto object);
    UserResponseDto update(UserRequestDto object);
    void activeDelete(Long id);
    UserResponseDto convertToDto(User user);
}

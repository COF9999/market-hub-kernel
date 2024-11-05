package com.market_hub.kernel.master.global.domain.dtos.user;

import com.market_hub.kernel.master.global.domain.dtos.token.TokenDto;

public record UserResponseDto(UserRequestDto userRequestDto,
                              TokenDto tokenDto) {
}

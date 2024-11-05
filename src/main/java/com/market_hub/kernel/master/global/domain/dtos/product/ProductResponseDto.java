package com.market_hub.kernel.master.global.domain.dtos.product;

public record ProductResponseDto(Long id,
                                 String category,
                                 Double price,
                                 Byte condition,
                                 String name,
                                 String imgUrl,
                                 String description
                                    )
{
}

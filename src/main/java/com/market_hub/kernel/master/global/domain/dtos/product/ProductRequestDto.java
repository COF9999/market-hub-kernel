package com.market_hub.kernel.master.global.domain.dtos.product;

import com.market_hub.kernel.master.global.domain.dtos.token.TokenInfo;
import org.springframework.web.multipart.MultipartFile;

public record ProductRequestDto(Long id,
                                String category,
                                Float price,
                                Byte condition,
                                String name,
                                String imageUrl,
                                MultipartFile multipartFile,
                                String newFileName,
                                String description,
                                TokenInfo tokenInfo) {
}

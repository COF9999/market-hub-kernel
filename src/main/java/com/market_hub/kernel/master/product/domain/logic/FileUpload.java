package com.market_hub.kernel.master.product.domain.logic;

import com.market_hub.kernel.master.global.infraestructure.advicers.exceptions.InvalidImageFormatException;
import com.market_hub.kernel.master.user.infraestructure.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface FileUpload {
    String uploadImg(MultipartFile multipartFile, String newFileName, User user) throws Exception;
}

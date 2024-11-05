package com.market_hub.kernel.master.product.infraestructure.adapters.implementations;

import com.market_hub.kernel.master.global.infraestructure.advicers.exceptions.InternalServerException;
import com.market_hub.kernel.master.global.infraestructure.advicers.exceptions.InvalidImageFormatException;
import com.market_hub.kernel.master.global.infraestructure.advicers.exceptions.ResourceAlreadyExists;
import com.market_hub.kernel.master.product.domain.logic.FileUpload;
import com.market_hub.kernel.master.user.infraestructure.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FileUploadLocalDesktopAdapter implements FileUpload {

    private final String BASE_UPLOAD_DIR = "/home/christian09/Documents/markethub/kernel.master/src/main/resources/static/images";
    private final List<String> ALLOWED_FILE_TYPES = Arrays.asList("image/jpeg", "image/png","image/jpg");

    @Override
    public String uploadImg(MultipartFile file, String newFileName, User user) throws Exception {
        if (file.isEmpty()) {
            throw new InvalidImageFormatException("Please select a file to upload");
        }

        if (!ALLOWED_FILE_TYPES.contains(file.getContentType())) {
            throw new InvalidImageFormatException("Only JPEG, PNG and JPG files are allowed");
        }

        try {
            String sanitizedFileName = "";
            if(newFileName.equalsIgnoreCase("")){
                sanitizedFileName = Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\\s+", "_");
            }else {
                String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
                sanitizedFileName = newFileName.replaceAll("\\s+", "_") + extension;
            }


            String pathFolder1 = user.getEmail().replaceAll("\\s+", "");
            String pathFolder2 = user.getUsername().replaceAll("\\s+", "");
            String path = Paths.get(pathFolder1,pathFolder2).toString();
            String dirReturn = path+"/"+sanitizedFileName;
            String fullDirPath = Paths.get(BASE_UPLOAD_DIR, pathFolder1, pathFolder2).toString();
            File dir = new File(fullDirPath);
            // Create the directory if it does not exist
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Ensure the new file name does not have spaces and append the correct extension


            // Save the file to the specified directory
            String filePath = Paths.get(fullDirPath, sanitizedFileName).toString();
            System.out.println(dirReturn);
            File newFile = new File(filePath);
            if (newFile.exists()) {
                throw new ResourceAlreadyExists("File already exists with the same name");
            }

            file.transferTo(new File(filePath));

            return dirReturn;

        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerException("ERROR DEL SERVIDOR AL CARGAR LA IMAGEN");
        }
    }
}

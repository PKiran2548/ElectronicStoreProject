package com.example.curd.opperation.serviceImpl;

import com.example.curd.opperation.controller.UserController;
import com.example.curd.opperation.exception.BadApiRequest;
import com.example.curd.opperation.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public String uploadImage(MultipartFile file, String path) throws IOException {

        String originalFilename = file.getOriginalFilename();
        log.info("orignal file name : {}" +originalFilename);

        String fileName = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.indexOf("."));

        String fileNameWithExtension = fileName + extension;

        String fullPathWithFileName = path + File.separator + fileNameWithExtension ;

        if (extension.equalsIgnoreCase(".png")|| extension.equalsIgnoreCase(".jepg")){

            File folder = new File(path);

            if (!folder.exists()){
                folder.mkdirs(); // create folder
            }

            Files.copy(file.getInputStream() , Paths.get(fullPathWithFileName));

        }else{
                throw new BadApiRequest("File with this  "+ extension +"  is not allowed");

        }


        return fullPathWithFileName;
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        
        String fullPath = path + File.separator + name ;

        InputStream inputStream = new FileInputStream(fullPath);

        return inputStream;
    }
}

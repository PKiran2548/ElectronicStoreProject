package com.example.curd.opperation.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    public String uploadImage (MultipartFile file , String path) throws IOException;

    public InputStream  getResource (String path , String name) throws FileNotFoundException;
}

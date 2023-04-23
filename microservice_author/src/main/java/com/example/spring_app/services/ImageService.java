package com.example.spring_app.services;
import com.example.spring_app.entities.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<Image> getAllImages();
    Resource getImageById(String id);
    void saveImage(MultipartFile file);
    void deleteImageById(String id);
}
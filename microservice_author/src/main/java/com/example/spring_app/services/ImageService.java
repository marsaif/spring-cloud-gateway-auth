package com.example.spring_app.services;
import com.example.spring_app.entities.Author;
import com.example.spring_app.entities.Image;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Page<Image> getAllImages(Pageable pageable);
    Resource getImageByName(String id);
    void saveImage(MultipartFile file);
    void deleteImageById(String id);
}
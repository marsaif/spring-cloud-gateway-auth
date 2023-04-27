package com.example.spring_app.services.impl;

import com.example.spring_app.entities.Image;
import com.example.spring_app.exceptions.NotFoundException;
import com.example.spring_app.repositories.ImageRepository;
import com.example.spring_app.services.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final Path root = Paths.get("uploads");

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Page<Image> getAllImages(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }

    @Override
    public Resource getImageByName(String name) {
        Image image = imageRepository.findByName(name).orElseThrow(() -> new NotFoundException("Image", "name"));

        try {
            Path file = root.resolve(image.getName());
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }

    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    @Override
    public void saveImage(MultipartFile file) {
        Image image = new Image();

        String fileName = UUID.randomUUID() + "." + getFileExtension(file.getOriginalFilename());

        image.setName(fileName);
        imageRepository.save(image);

        try {
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("A file of that name already exists.");
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred: " + e.getMessage());
        }
    }

    @Override
    public void deleteImageById(String id)  {
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image", "id"));
        imageRepository.delete(image);
        Path filePath = root.resolve(image.getName());
        try {
            Files.delete(filePath);
        } catch (NoSuchFileException e) {
            throw new NotFoundException("Image file not found", "id");
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image file: " + filePath.toString() + ", " + e.getMessage());
        }
    }
}
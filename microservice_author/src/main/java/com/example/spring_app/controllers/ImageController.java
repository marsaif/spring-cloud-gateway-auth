package com.example.spring_app.controllers;

import com.example.spring_app.entities.Image;
import com.example.spring_app.models.apiResponse.ApiResponse;
import com.example.spring_app.services.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/images")
public class ImageController {

        private final ImageService imageService;

        public ImageController(ImageService imageService) {
            this.imageService = imageService;
        }

        @GetMapping
        public ResponseEntity<ApiResponse<List<Image>>> getAllImages(Pageable pageable) {
            return ResponseEntity.ok(new ApiResponse(imageService.getAllImages(pageable)));
        }

        @GetMapping("/{name}")
        public ResponseEntity<Resource> getImageById(@PathVariable String name) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageService.getImageByName(name));
        }

        @PostMapping
        public ResponseEntity<ApiResponse<String>> saveImage(@RequestParam("file") MultipartFile file) {
            imageService.saveImage(file);
            return ResponseEntity.ok(new ApiResponse<>("image Saved"));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<String>> deleteImageById(@PathVariable String id) {
            imageService.deleteImageById(id);
            return ResponseEntity.ok(new ApiResponse<>("image Deleted"));
        }
}
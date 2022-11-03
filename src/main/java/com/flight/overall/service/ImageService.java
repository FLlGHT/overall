package com.flight.overall.service;


import com.flight.overall.entity.Image;
import com.flight.overall.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Optional<Image> uploadImage(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty())
            return Optional.empty();
        try {
            Image image = new Image();
            image.setName(multipartFile.getOriginalFilename());
            image.setContent(multipartFile.getBytes());

            return Optional.of(imageRepository.save(image));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}

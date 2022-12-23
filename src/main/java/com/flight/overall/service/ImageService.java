package com.flight.overall.service;


import com.flight.overall.dto.ImageDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Image;
import com.flight.overall.entity.Profile;
import com.flight.overall.repository.ImageRepository;
import com.flight.overall.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public Optional<Image> uploadImage(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty())
            return Optional.empty();
        try {
            Image image = new Image();
            image.setName(multipartFile.getOriginalFilename());
            image.setContent(compressBytes(multipartFile.getBytes()));

            return Optional.of(imageRepository.save(image));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public void saveProfileImage(MultipartFile multipartFile, Profile profile) {
        Optional<Image> newImage = uploadImage(multipartFile);
        newImage.ifPresent(image -> {
            profile.setProfileImage(image);
            profileRepository.save(profile);
        });
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            //
        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application

}

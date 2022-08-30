package com.example.service;

import com.example.entity.ImageData;
import com.example.repository.ImageDataRepository;
import com.example.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    @Autowired
    private ImageDataRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData!=null) {
            return "file uploaded successfully : "+file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String filename) {
        Optional<ImageData> dbImageData = repository.findByName(filename);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}

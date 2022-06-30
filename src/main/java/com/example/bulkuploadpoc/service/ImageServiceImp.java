package com.example.bulkuploadpoc.service;

import com.example.bulkuploadpoc.dao.ImageService;
import com.example.bulkuploadpoc.helper.ImageFileValidator;
import com.example.bulkuploadpoc.model.ImageModel;
import com.example.bulkuploadpoc.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImp implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public void uploadImage(String title, MultipartFile file) {
        try {
            ImageModel imageModel = ImageFileValidator.imageValidated(title, file);
            imageRepository.insert(imageModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

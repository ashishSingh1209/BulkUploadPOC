package com.example.bulkuploadpoc.service;

import com.example.bulkuploadpoc.dao.ImageService;
import com.example.bulkuploadpoc.helper.ImageFileValidator;
import com.example.bulkuploadpoc.model.ImageModel;
import com.example.bulkuploadpoc.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;

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

    @Override
    public String getImage(String id) {

        byte[] data = Base64.getDecoder().decode(Base64.getEncoder().encodeToString(imageRepository.findById(id).get().getImage().getData()));
        String path = "//home//usl-sz-424//Desktop//OutPut//" + imageRepository.findById(id).get().getTitle() +
                "." + imageRepository.findById(id).get().getImageExtension();
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "file is downloaded in directory: " + file.getAbsolutePath();
    }
}

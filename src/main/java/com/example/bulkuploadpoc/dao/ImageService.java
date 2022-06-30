package com.example.bulkuploadpoc.dao;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void uploadImage(String title, MultipartFile file);
}

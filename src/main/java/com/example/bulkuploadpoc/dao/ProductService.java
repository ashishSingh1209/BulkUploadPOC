package com.example.bulkuploadpoc.dao;

import com.example.bulkuploadpoc.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    void save(MultipartFile file);

    List<Product> getAllData();



    String getCsvInDirectory();
}

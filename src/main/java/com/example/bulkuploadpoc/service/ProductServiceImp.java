package com.example.bulkuploadpoc.service;

import com.example.bulkuploadpoc.dao.ProductService;
import com.example.bulkuploadpoc.helper.CSVHelper;
import com.example.bulkuploadpoc.model.Product;
import com.example.bulkuploadpoc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository userRepository;

    @Override
    public void save(MultipartFile file) {
        try {
            List<Product> tutorials = CSVHelper.csvToProductList(file.getInputStream());
            userRepository.saveAll(tutorials);

        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllData() {
        return userRepository.findAll();
    }



    @Override
    public String getCsvInDirectory() {
    return   CSVHelper.productListToCSV(userRepository.findAll());

    }
}


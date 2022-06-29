package com.example.bulkuploadpoc.helper;


import com.example.bulkuploadpoc.model.Product;
import com.example.bulkuploadpoc.model.ProductVendor;
import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;

    }

    public static List<Product> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Product> products = new ArrayList<Product>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Product product = Product.builder()
                        .name(csvRecord.get("name"))
                        ._id(Integer.parseInt(csvRecord.get("_id")))
                        .brand(csvRecord.get("brand"))
                        .color(csvRecord.get("color"))
                        .description(csvRecord.get("description"))
                        .productType(csvRecord.get("productType"))
                        .status(csvRecord.get("status"))
                        .shopCategories(listParser(csvRecord.get("shopCategories")))
                        .internalCategories(listParser(csvRecord.get("internalCategories")))
                        .weight(Float.parseFloat(csvRecord.get("weight")))
                        .productVendor(productVendor(csvRecord.get("productVendors")))
                        .build();
                products.add(product);
            }
            return products;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ProductVendor productVendor(String str) {
        if (!Objects.equals(str, "")) {
            String parseString = str.substring(1, str.length() - 1);
            Gson gson = new Gson();
            return gson.fromJson(parseString, ProductVendor.class);
        } else return null;
    }

    public static List<String> listParser(String str) {
        String newString = str.substring(1, str.length() - 2);
        String[] asList = newString.split(",");
        List<String> parsedList = new ArrayList<>();
        for (String s : asList) {
            parsedList.add(s.substring(1, s.length() - 1));
        }
        return parsedList;
    }
    //New change is here

}


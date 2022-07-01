package com.example.bulkuploadpoc.helper;


import com.example.bulkuploadpoc.model.Product;
import com.example.bulkuploadpoc.model.ProductVendor;
import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;

    }

    public static List<Product> csvToProductList(InputStream is) {
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

    public static String productListToCSV(List<Product> list) {
        String fileName = UUID.randomUUID().toString();
        String csvFilePath = "//home//usl-sz-424//Desktop//OutPut//" + fileName + ".csv";
        File file = new File(csvFilePath);


        try {
            FileWriter outputFile = new FileWriter(file);
            ICsvBeanWriter beanWriter = new CsvBeanWriter(outputFile, CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"_id", "brand", "color", "description", "internalCategories", "name", "productType",
                    "shopCategories", "productVendor", "status", "weight"};
            beanWriter.writeHeader(csvHeader);
            String[] nameMapping = {"_id", "brand", "color", "description", "internalCategories", "name", "productType",
                    "shopCategories", "productVendor", "status", "weight"};


            for (Product product : list) {
                beanWriter.write(product, nameMapping);
            }
            beanWriter.close();
            return "Fetched to: " + csvFilePath;


        } catch (Exception e) {
            return "Failed to fetched csv file: " + e.getMessage();
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

}


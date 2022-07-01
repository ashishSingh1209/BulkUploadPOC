package com.example.bulkuploadpoc.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVendor {
    private String vendorSid;
    private String vendorName;
    private String vendorSku;
    private Float baseCost;
    private String status;
}

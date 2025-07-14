package com.example.inventory_capstone.model;


import java.math.BigDecimal;

public class Product {
    private String productID;
    private String productName;



    public Product(String productID, String productName) {
        this.productID = productID;
        this.productName = productName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return String.format("ID: '%s' \nName: %s", productID, productName);
    }
}
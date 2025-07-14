package com.example.capstone_inventory.model;

import java.time.LocalDate;

public class PerishableProduct extends Product{
    private LocalDate expirationDate;


    public PerishableProduct(String productID, String productName, LocalDate expirationDate) {
        super(productID, productName);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nExpiration Date: %s", expirationDate);
    }
}

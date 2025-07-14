package com.example.inventory_capstone.model;

import java.math.BigDecimal;

public class InventoryItem {
    private final Product product;
    private int quantity;
    private BigDecimal price;

    public InventoryItem(Product product, int quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be less than 0.");
        }
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s - Stock: %d - Price: %.2f", product.toString(), quantity, price);
    }
}

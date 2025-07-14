package com.example.inventory_capstone.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CartItem {
    private final Product product;
    private int quantity;
    private BigDecimal price;
    private BigDecimal extendedPrice;

    public CartItem(Product product, int quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.extendedPrice = price.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be less than 0.");
        }
        this.price = price;
    }

    public BigDecimal getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(BigDecimal extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @Override
    public String toString() {
        return String.format("%s - Quantity: %d - Price per item: $%s Total price: $%s",
                product.getProductName(), quantity, price, extendedPrice);
    }
}

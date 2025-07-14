package com.example.inventory_capstone.service;

import com.example.inventory_capstone.model.*;
import com.example.inventory_capstone.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final InventoryRepository inventoryRepository;
    private final HashMap<String, CartItem> cart = new HashMap<>();

    @Autowired
    public CartService(InventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    public Result<Void> addToCart(String productID, int quantity) {
        if (productID == null || productID.trim().isEmpty()) {
            return new Result<>(false, "No productID detected", null);
        }
        if (quantity < 0) {
            return new Result(false, "Quantity cannot be negative", null);
        }
        InventoryItem item = inventoryRepository.getByID(productID);
        if (item == null) {
            return new Result<>(false, "Item not found in inventory " + productID, null);
        }

        if (item.getProduct() instanceof PerishableProduct pp &&
                pp.getExpirationDate().isBefore(LocalDate.now())) {
            return new Result<>(false, "Item is expired,", null);
        }

        int currentCartQuantity = cart.containsKey(productID) ? cart.get(productID).getQuantity() : 0;
        int newTotalQuantity = currentCartQuantity + quantity;

        if (newTotalQuantity > item.getQuantity()) {
            return new Result<>(false, String.format("Not enough %s(s) in stock. Available: %d, Requested: %d",
                    item.getProduct().getProductName(), item.getQuantity(), newTotalQuantity), null);
        }
        if (item.getProduct() instanceof PerishableProduct pp &&
                pp.getExpirationDate().isBefore(LocalDate.now())) {
            return new Result<>(false, "Item is expired,", null);
        }

        if (cart.containsKey(productID)) {
            CartItem existingCartItem = cart.get(productID);
            existingCartItem.setQuantity(newTotalQuantity);
        } else {
            BigDecimal price = item.getPrice();
            CartItem newCartItem = new CartItem(item.getProduct(), newTotalQuantity, price);
            cart.put(productID, newCartItem);
        }

        return new Result<>(true, String.format("Added %d %s(s) to cart", quantity, item.getProduct().getProductName()), null);
    }

    public Result<Void> removeFromCart(String productID, int quantity) {
        if (productID == null || productID.trim().isEmpty()) {
            return new Result<>(false, "No productID detected", null);
        }
        if (quantity < 0) {
            return new Result(false, "Quantity cannot be negative", null);
        }
        if (!cart.containsKey(productID)) {
            return new Result<>(false, "Item not found in cart", null);
        }

        CartItem cartItem = cart.get(productID);
        int currentQuantity = cartItem.getQuantity();
        String name = cartItem.getProduct().getProductName();

        if (quantity >= currentQuantity) {
            cart.remove(productID);
            return new Result<>(true, String.format("Removed all %s(s) from cart", cartItem.getProduct().getProductName()), null);
        } else {
            cartItem.setQuantity(currentQuantity - quantity);
            return new Result<>(true, String.format("Removed %d %s(s) from your cart", quantity, cartItem.getProduct().getProductName()), null);
        }
    }

    public Result<BigDecimal> getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cart.values()) {
            total = total.add(cartItem.getExtendedPrice());
        }

        return new Result<>(true, "Successfully calculated total", total.setScale(2, RoundingMode.HALF_UP));
    }

    public Result<String> checkout() {
        if (cart.isEmpty()) {
            return new Result<>(false, "No items in cart", null);
        }

        Result<BigDecimal> totalResult = getTotalPrice();
        if (!totalResult.isSuccess()) {
            return new Result<>(false, totalResult.getMessage(), null);
        }
        BigDecimal total = totalResult.getData();

        for (HashMap.Entry<String, CartItem> entry : cart.entrySet()) {
            String productID = entry.getKey();
            CartItem cartItem = entry.getValue();
            int purchasedQuantity = cartItem.getQuantity();

            InventoryItem item = inventoryRepository.getByID(productID);
            item.setQuantity(item.getQuantity() - purchasedQuantity);
            inventoryRepository.update(item);
        }

        cart.clear();

        return new Result<>(true, String.format("Checkout complete! Total: $%s", total), null);
    }

    public ArrayList<CartItem> getItemsInCart() {
        return new ArrayList<>(cart.values());
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public List<InventoryItem> getAvailableInventory() {
        return sortInventoryByProductID(inventoryRepository.getAll().stream()
                .filter(item -> item.getQuantity() > 0)
                .filter(item -> {
                    Product product = item.getProduct();
                    if (product instanceof PerishableProduct pp) {
                        return !pp.getExpirationDate().isBefore(LocalDate.now());
                    }
                    return true;
                })
                .collect(Collectors.toList()));
    }

    public InventoryItem getItem(String productID) {
        return inventoryRepository.getByID(productID);
    }

    public List<InventoryItem> sortInventoryByProductID(List<InventoryItem> items) {
        return items.stream().sorted(Comparator.comparing(item -> item.getProduct().getProductID()))
                .collect(Collectors.toList());
    }

}



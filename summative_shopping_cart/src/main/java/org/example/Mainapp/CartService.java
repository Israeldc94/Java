package org.example.Mainapp;

import java.util.HashMap;


public class CartService {
    private HashMap<Item, Integer> cart;

    public CartService() {
        this.cart = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        cart.put(item,quantity);
    }

    public void removeItem(Item item, int quantity) {
        int currentQuantity = cart.get(item);
        int finalQuantity = currentQuantity - quantity;
        if (finalQuantity <= 0) {
            cart.remove(item);
        } else {
            cart.put(item, finalQuantity);
        }
    }

    public HashMap<Item, Integer> getCart() {
        return cart;
    }

    public double getTotal(){
        double total = 0.0;
        for (Item item : cart.keySet()) {
            int quantity = cart.get(item);
            total += item.getPrice() *quantity;
        }
        return total;
    }

    public void clearCart(){
        cart.clear();
    }
    public Item getItemByName(String itemName){
        for(Item item : cart.keySet()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;

            }else{

                return null;
            }

        }
        return null;
    }


}

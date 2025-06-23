package org.example.Mainapp;

import static org.example.Mainapp.App.io;

public class ItemFactory {

    public Item createItem(String itemName, double price) {
        Item item = new Item(itemName, price);
        return item;
    }
}

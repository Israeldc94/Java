package com.example.inventory_capstone.view;

import com.example.inventory_capstone.model.InventoryItem;
import com.example.inventory_capstone.service.CartService;
import com.example.inventory_capstone.service.InventoryService;
import com.example.inventory_capstone.view.customer_commands.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerWorkflow {

    private final CartService service;
    private final CartConsoleIO io;

    @Autowired
    public CustomerWorkflow(CartService service, CartConsoleIO io) {
        this.service = service;
        this.io = io;
    }

    public void run() {
        boolean keepRunning = true;

        ViewAvailableInventoryCommand viewInventory = new ViewAvailableInventoryCommand(service, io);
        ViewItemCommand viewItem = new ViewItemCommand(service, io);
        ViewCartCommand viewCart = new ViewCartCommand(service, io);
        UpdateOrAddItemCommand updateOrAddItem = new UpdateOrAddItemCommand(service,io);
        RemoveItemCommand removeItem = new RemoveItemCommand(service, io);
        CheckoutCommand checkout = new CheckoutCommand(service, io);

        io.displayStart();

        while (keepRunning) {
            io.printMenu();
            int choice = io.getInt("Please select an option:", 1, 7);
            CustomerMenuOptions selected = CustomerMenuOptions.values()[choice - 1];

            switch (selected) {
                case VIEW_AVAILABLE_STOCK:
                    viewInventory.execute();
                    break;
                case VIEW_ITEM:
                    viewItem.execute();
                    break;
                case VIEW_CART:
                    viewCart.execute();
                    break;
                case UPDATE_OR_ADD_ITEM:
                    updateOrAddItem.execute();
                    break;
                case REMOVE_ITEM:
                    removeItem.execute();
                    break;
                case CHECKOUT:
                    checkout.execute();
                    break;
                case EXIT:
                    keepRunning = false;
                    break;
            }
        }

        io.displayEnd();
    }
}
package com.example.inventory_capstone.view;

import com.example.inventory_capstone.service.InventoryService;
import com.example.inventory_capstone.view.admin_commands.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

@Component
public class Adminworkflow {

    private final InventoryService service;
    private final InventoryConsoleIO io;
    private final String password = "admin";

    @Autowired
    public Adminworkflow(InventoryService service, InventoryConsoleIO inventoryIO) {
        this.service = service;
        this.io = inventoryIO;
    }


    public void run() {
        boolean keepRunning = true;
        ViewAvailableInventoryCommand viewCustomerInventory = new ViewAvailableInventoryCommand(service,io);
        ViewAllInventoryCommand viewAllInventory = new ViewAllInventoryCommand(service,io);
        ViewItemCommand viewItem = new ViewItemCommand(service,io);
        UpdateOrAddItemCommand updateOrAddItem = new UpdateOrAddItemCommand(service,io);
        RemoveItemCommand removeItem = new RemoveItemCommand(service,io);

        String enteredPassword = io.authenticateUser();

        if (!enteredPassword.equals(password)) {
            io.print("incorrect password");
            io.authenticateUser();
        }

        io.displayStart();

        while (keepRunning) {
            io.printMenu();

            int choice = io.getInt("Please select an option", 1, 6);
            AdminMenuOptions selected = AdminMenuOptions.values()[choice - 1];

            switch (selected) {
                case VIEW_AVAILABLE_INVENTORY:
                    viewCustomerInventory.execute();
                    break;
                case VIEW_ALL_INVENTORY:
                    viewAllInventory.execute();
                    break;
                case VIEW_ITEM:
                    viewItem.execute();
                    break;
                case UPDATE_OR_ADD_ITEM:
                    updateOrAddItem.execute();
                    break;
                case REMOVE_ITEM:
                    removeItem.execute();
                    break;
                case EXIT:
                    keepRunning = false;

            }


        }
        io.displayEnd();

    }

}

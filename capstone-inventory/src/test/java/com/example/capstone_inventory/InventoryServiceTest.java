package com.example.capstone_inventory;

import com.example.capstone_inventory.model.InventoryItem;
import com.example.capstone_inventory.repository.CsvInventoryRepository;
import com.example.capstone_inventory.repository.InventoryRepository;
import com.example.capstone_inventory.service.InventoryService;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    private static final String SEED_FILE = "data/seed_inventory.csv";
    private static final String TEST_FILE = "data/test_inventory.csv";

    private InventoryRepository inventoryRepository;
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() throws IOException {
        Files.copy(Path.of(SEED_FILE), Path.of(TEST_FILE), StandardCopyOption.REPLACE_EXISTING);
        inventoryRepository = new CsvInventoryRepository(TEST_FILE);
        inventoryService = new InventoryService(inventoryRepository);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void getCustomerAvailableInventory_returnsNonExpiredAndInStockItems() {
        List<InventoryItem> inventory = inventoryService.getCustomerAvailableInventory();
        assertFalse(inventory.isEmpty());
        assertTrue(inventory.stream().allMatch(item -> item.getQuantity() > 0 ));
    }


    @Test
    void getCustomerAvailableInventory_excludesExpiredAndOutOfStock() {
        List<String> productIDs = inventoryService.getCustomerAvailableInventory()
                .stream()
                .map(item -> item.getProduct().getProductID())
                .collect(Collectors.toList());

        assertFalse(productIDs.contains("9999"));
        assertFalse(productIDs.contains("0000"));
        assertFalse(productIDs.isEmpty());
    }

}

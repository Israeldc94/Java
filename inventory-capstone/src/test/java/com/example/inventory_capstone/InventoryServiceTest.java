package com.example.inventory_capstone;

import com.example.inventory_capstone.model.InventoryItem;
import com.example.inventory_capstone.model.Product;
import com.example.inventory_capstone.repository.CsvInventoryRepository;
import com.example.inventory_capstone.repository.InventoryRepository;
import com.example.inventory_capstone.service.InventoryService;
import org.junit.jupiter.api.*;

import java.io.*;
import java.math.BigDecimal;
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

    @Test
    void updateOrAddItem_updatesExistingItem() {
        InventoryItem original = inventoryRepository.getByID("0001");
        int originalQty = original.getQuantity();

        InventoryItem updatedItem = new InventoryItem(original.getProduct(), original.getQuantity(), original.getPrice());
        updatedItem.setQuantity(99);
        updatedItem.setPrice(new BigDecimal("1.23"));

        inventoryService.updateOrAddItem(updatedItem);

        InventoryItem updated = inventoryRepository.getByID("0001");
        assertEquals(originalQty + 99, updated.getQuantity());
        assertEquals(new BigDecimal("1.23"), updated.getPrice());
    }

    @Test
    void updateOrAddItem_addsNewItem() {
        InventoryItem newItem = new InventoryItem(
                new Product("1234", "New Product"),
                10,
                new BigDecimal("9.99")
        );
        inventoryService.updateOrAddItem(newItem);
        InventoryItem retrieved = inventoryRepository.getByID("1234");
        assertNotNull(retrieved);
        assertEquals("New Product", retrieved.getProduct().getProductName());
    }

    @Test
    void removeItem_removesFromInventory() {
        inventoryService.removeItem("0002");
        assertNull(inventoryRepository.getByID("0002"));
    }

    void getItem_returnsCorrectItem() {
        InventoryItem item = inventoryService.getItem("0003");
        assertNotNull(item);
        assertEquals("Wipes", item.getProduct().getProductName());
    }

    @Test
    void getAllItems_returnsAllIncludingExpiredAndOutOfStock() {
        List<InventoryItem> allItems = inventoryService.getAllItems();
        boolean containsExpired = allItems.stream().anyMatch(item ->
                item.getProduct().getProductName().equals("Expired Yogurt"));
        boolean containsOutOfStock = allItems.stream().anyMatch(item ->
                item.getQuantity() == 0);
        assertTrue(containsExpired);
        assertTrue(containsOutOfStock);
    }


}

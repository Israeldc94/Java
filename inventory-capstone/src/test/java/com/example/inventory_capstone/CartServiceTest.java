package com.example.inventory_capstone;
// File: CartServiceTest.java


import com.example.inventory_capstone.model.InventoryItem;
import com.example.inventory_capstone.model.Product;
import com.example.inventory_capstone.repository.CsvInventoryRepository;
import com.example.inventory_capstone.repository.InventoryRepository;
import com.example.inventory_capstone.service.InventoryService;
import org.junit.jupiter.api.*;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {

    private static final String SEED_FILE = "data/seed_inventory.csv";
    private static final String TEST_FILE = "data/test_inventory.csv";

    private InventoryRepository inventoryRepository;
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() throws IOException {
        try (InputStream is = new FileInputStream(SEED_FILE);
             OutputStream os = new FileOutputStream(TEST_FILE)) {
            is.transferTo(os);
        }
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
    void getCustomerAvailableInventory_returnsOnlyValidItems() {
        List<String> productNames = inventoryService.getCustomerAvailableInventory()
                .stream()
                .map(item -> item.getProduct().getProductName())
                .collect(Collectors.toList());

        assertTrue(productNames.contains("Paper Towels"), "In-stock, non-expired item should be included");
        assertFalse(productNames.contains("Expired Yogurt"), "Expired items should not be included in customer inventory");
        assertFalse(productNames.contains("Nonexistent Towels"), "Zero stock items should not be included in customer inventory");
        assertFalse(productNames.isEmpty(), "There should be available products for customers");
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

    @Test
    void getItem_returnsCorrectItem() {
        InventoryItem item = inventoryService.getItem("0003");
        assertNotNull(item);
        assertEquals("Wipes", item.getProduct().getProductName());
    }

    @Test
    void getAllItems_returnsAllIncludingExpiredAndOutOfStock() {
        List<InventoryItem> allItems = inventoryService.getAllItems();
        List<String> productNames = allItems.stream()
                .map(item -> item.getProduct().getProductName())
                .collect(Collectors.toList());

        assertTrue(productNames.contains("Expired Yogurt"), "Expired items should be returned in getAllItems()");
        assertTrue(productNames.contains("Nonexistent Towels"), "Out-of-stock items should be returned in getAllItems()");
        assertTrue(productNames.contains("Paper Towels"), "Valid in-stock item should also be included");
        assertFalse(productNames.isEmpty(), "Inventory list should not be empty");
    }
}

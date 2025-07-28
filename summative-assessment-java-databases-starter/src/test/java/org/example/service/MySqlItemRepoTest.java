package org.example.service;

import org.example.data.ItemRepo;
import org.example.data.impl.Item.MySqlItemRepo;
import org.example.model.Item;
import org.example.model.ItemCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlItemRepoTest {
    private ItemRepo repo;

    @BeforeEach
    void setup() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/SimpleBistroTest");
        ds.setUsername("root");
        ds.setPassword("Cerseiwatch304!");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.execute("CALL set_known_good_state()");

        repo = new MySqlItemRepo(jdbcTemplate);
    }

    @Test
    void shouldReturnItemByID() throws Exception {
        Item item = repo.getItemById(1);
        assertNotNull(item);
        assertEquals(1, item.getItemID());
        assertEquals("Mozzerella Sticks", item.getItemName());
    }

    @Test
    void shouldReturnAvailableItems() throws Exception {
        List<Item> items = repo.getAllAvailableItems(LocalDate.now());
        assertFalse(items.isEmpty());
    }

    @Test
    void shouldReturnItemsByCategory() throws Exception {
        List<Item> items = repo.getItemsByCategory(LocalDate.now(), 1);
        assertTrue(items.stream().allMatch(i -> i.getItemCategoryID() == 1));
    }

    @Test
    void shouldReturnAllCategories() throws Exception {
        List<ItemCategory> categories = repo.getAllItemCategories();
        assertFalse(categories.isEmpty());
    }
}

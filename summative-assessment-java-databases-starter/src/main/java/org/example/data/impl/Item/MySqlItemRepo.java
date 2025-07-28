package org.example.data.impl.Item;

import org.example.data.ItemRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Item;
import org.example.model.ItemCategory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MySqlItemRepo implements ItemRepo {
    private final JdbcTemplate jdbcTemplate;

    public MySqlItemRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item getItemById(int id) throws RecordNotFoundException, InternalErrorException {
        try {
            final String sql = """
                    SELECT i.*, ic.ItemCategoryName
                    FROM Item i
                    JOIN ItemCategory ic ON i.ItemCategoryID = ic.ItemCategoryID
                    WHERE i.ItemID = ?
                    """;
                return jdbcTemplate.queryForObject(sql, new ItemMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException();
        } catch (DataAccessException ex) {
            throw new InternalErrorException();
        }
    }

    @Override
    public List<Item> getAllAvailableItems(LocalDate today) throws InternalErrorException {
        try {
            final String sql = """
                    SELECT i.*, ic.ItemCategoryName
                    FROM Item i
                    JOIN ItemCategory ic ON i.ItemCategoryID = ic.ItemCategoryID
                    WHERE i.StartDate <= ? AND (i.EndDate IS NULL OR i.EndDate >= ?)
                    """;
            return jdbcTemplate.query(sql,new ItemMapper(), today, today);
        } catch (DataAccessException ex) {
            throw new InternalErrorException();
        }
    }

    @Override
    public List<Item> getItemsByCategory(LocalDate today, int itemCategoryID) throws InternalErrorException {
        try {
            final String sql = """
                    SELECT i.*, ic.ItemCategoryName
                    FROM Item i
                    JOIN ItemCategory ic ON i.ItemCategoryID = ic.ItemCategoryID
                    WHERE ? BETWEEN i.StartDate AND i.EndDate
                    AND i.ItemCategoryID = ?
                    """;
            return jdbcTemplate.query(sql, new ItemMapper(), today, itemCategoryID);
        } catch (DataAccessException ex) {
            throw new InternalErrorException();
        }
    }

    @Override
    public List<ItemCategory> getAllItemCategories() throws InternalErrorException {
        try {
            final String sql = "SELECT * FROM ItemCategory";
            return jdbcTemplate.query(sql, new ItemCategoryMapper());
        } catch (DataAccessException ex) {
            throw new InternalErrorException();
        }
    }
}

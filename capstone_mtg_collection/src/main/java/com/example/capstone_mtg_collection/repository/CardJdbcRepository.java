package com.example.capstone_mtg_collection.repository;

import com.example.capstone_mtg_collection.mapper.CardMapper;
import com.example.capstone_mtg_collection.model.Card;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CardJdbcRepository implements CardRepository{

    private final JdbcTemplate jdbcTemplate;
    private final CardMapper cardMapper;

    public CardJdbcRepository(JdbcTemplate jdbcTemplate, CardMapper cardMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.cardMapper = cardMapper;
    }

    @Override
    public List<Card> findAll() {
        String sql = "SELECT * FROM card";
        return jdbcTemplate.query(sql, cardMapper);
    }

    @Override
    public Card findById(int id) {
        String sql = "SELECT * FROM card WHERE card_id = ?";
        return jdbcTemplate.queryForObject(sql, cardMapper, id);
    }

    @Override
    public int insert(Card card) {
        String sql = """
                INSERT INTO card (name, mana_cost, color, type_line, foil, quantity_owned, image_url)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                card.getName(),
                card.getManaCost(),
                card.getColor(),
                card.getTypeLine(),
                card.isFoil(),
                card.getQuantityOwned(),
                card.getImageUrl()
        );
    }

    @Override
    public int update(Card card) {
        String sql = """
                UPDATE card SET
                    name = ?,
                    mana_cost = ?,
                    color = ?,
                    type_line = ?,
                    foil = ?,
                    quantity_owned = ?,
                    image_url = ?
                WHERE card_id = ?
                """;
        return jdbcTemplate.update(sql,
                card.getName(),
                card.getManaCost(),
                card.getColor(),
                card.getTypeLine(),
                card.isFoil(),
                card.getQuantityOwned(),
                card.getImageUrl(),
                card.getCardId()
        );
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM card WHERE card_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Card> findByNameContainingIgnoreCase(String keyword) {
    String sql = """
            SELECT * FROM card
            WHERE LOWER(name) LIKE ?
            """;
    String pattern = "%" + keyword.toLowerCase() + "%";
    return jdbcTemplate.query(sql, cardMapper, pattern);
    }
}
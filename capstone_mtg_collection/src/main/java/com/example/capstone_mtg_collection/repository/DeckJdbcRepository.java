package com.example.capstone_mtg_collection.repository;

import com.example.capstone_mtg_collection.mapper.DeckMapper;
import com.example.capstone_mtg_collection.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeckJdbcRepository implements DeckRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DeckMapper deckMapper;

    public DeckJdbcRepository(JdbcTemplate jdbcTemplate, DeckMapper deckMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.deckMapper = deckMapper;
    }

    @Override
    public List<Deck> findAll() {
        String sql = "SELECT * FROM deck";
        return jdbcTemplate.query(sql, deckMapper);
    }

    @Override
    public Deck findById(int id) {
        String sql = "SELECT * FROM deck WHERE deck_id = ?";
        return jdbcTemplate.queryForObject(sql, deckMapper, id);
    }

    @Override
    public int insert(Deck deck) {
        String sql = """
                INSERT INTO deck (name, format, description)
                VALUES (?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                deck.getName(),
                deck.getFormat(),
                deck.getDescription()
        );
    }

    @Override
    public int update(Deck deck) {
        String sql = """
                UPDATE deck SET
                    name = ?,
                    format = ?,
                    description = ?
                WHERE deck_id = ?
                """;
        return jdbcTemplate.update(sql,
                deck.getName(),
                deck.getFormat(),
                deck.getDescription(),
                deck.getDeckId()
        );
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM deck WHERE deck_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

package com.example.capstone_mtg_collection.repository;

import com.example.capstone_mtg_collection.mapper.DeckMapper;
import com.example.capstone_mtg_collection.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        return jdbcTemplate.query("SELECT * FROM deck", deckMapper);
    }

    @Override
    public Deck findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM deck WHERE deck_id = ?",
                deckMapper, id
        );
    }

    /**
     * Inserts a deck and returns the new deck_id.
     */

    @Override
    public int insertAndReturnId(Deck deck) {
        final String sql = "INSERT INTO deck (name, format, notes) VALUES (?, ?, ?)";

        // Support both getNotes() and getDescription() to avoid breaking callers
        final String notes =
                deck.getNotes() != null ? deck.getNotes()
                        : (deck.getNotes() != null ? deck.getNotes() : null);

        KeyHolder kh = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, deck.getName());
            ps.setString(2, deck.getFormat());
            ps.setString(3, notes);
            return ps;
        }, kh);

        Number key = kh.getKey();
        return key != null ? key.intValue() : 0;
    }

    @Override
    public int insert(Deck deck) {
        final String sql = "INSERT INTO deck (name, format, notes) VALUES (?, ?, ?)";

        KeyHolder kh = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, deck.getName());
            ps.setString(2, deck.getFormat());
            // If your model still calls it "description", change this to getDescription().
            ps.setString(3, deck.getNotes());
            return ps;
        }, kh);

        Number key = kh.getKey();
        return key != null ? key.intValue() : 0; // or throw if null
    }

    @Override
    public int update(Deck deck) {
        final String sql = """
            UPDATE deck
               SET name = ?,
                   format = ?,
                   notes = ?
             WHERE deck_id = ?
        """;
        return jdbcTemplate.update(sql,
                deck.getName(),
                deck.getFormat(),
                // same note as above re: description vs notes
                deck.getNotes(),
                deck.getDeckId()
        );
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM deck WHERE deck_id = ?", id);
    }
}

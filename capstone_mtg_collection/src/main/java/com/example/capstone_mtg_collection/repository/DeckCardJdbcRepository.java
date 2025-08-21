package com.example.capstone_mtg_collection.repository;

import com.example.capstone_mtg_collection.mapper.CardMapper;
import com.example.capstone_mtg_collection.mapper.DeckCardMapper;
import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.DeckCard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class DeckCardJdbcRepository implements DeckCardRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CardMapper mapper;
    private final DeckCardMapper dcMapper;

    public DeckCardJdbcRepository(JdbcTemplate jdbcTemplate, CardMapper mapper, DeckCardMapper dcMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.dcMapper = dcMapper;
    }

    @Override
    public List<Card> findByDeckId(int deckId) {
        final String sql = """
            SELECT c.card_id, c.name, c.color, c.type_line, c.foil,
                   c.quantity_owned, c.image_url, c.mana_cost_text, c.mana_value
              FROM deck_card dc
              JOIN card c ON c.card_id = dc.card_id
             WHERE dc.deck_id = ?
             ORDER BY c.name ASC
        """;
        return jdbcTemplate.query(sql, mapper, deckId);
    }

    @Override
    public DeckCard findByDeckIdAndCardId(int deckId, int cardId) {
        String sql = "SELECT * FROM deck_card WHERE deck_id = ? AND card_id = ?";
        return jdbcTemplate.queryForObject(sql, dcMapper, deckId, cardId);
    }

    @Override
    public boolean addCardToDeck(DeckCard deckCard) {
        String sql = """
            INSERT INTO deck_card (deck_id, card_id, quantity)
            VALUES (?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                deckCard.getDeckId(),
                deckCard.getCardId(),
                deckCard.getQuantity()
        ) > 0;
    }

    @Override
    public boolean updateCardQuantityInDeck(DeckCard deckCard) {
        String sql = """
            UPDATE deck_card
            SET quantity = ?
            WHERE deck_id = ? AND card_id = ?
        """;
        return jdbcTemplate.update(sql,
                deckCard.getQuantity(),
                deckCard.getDeckId(),
                deckCard.getCardId()
        ) > 0;
    }

    @Override
    public boolean removeCardFromDeck(int deckId, int cardId) {
        String sql = "DELETE FROM deck_card WHERE deck_id = ? AND card_id = ?";
        return jdbcTemplate.update(sql, deckId, cardId) > 0;
    }

    @Override
    public boolean removeAllCardsFromDeck(int deckId) {
        String sql = "DELETE FROM deck_card WHERE deck_id = ?";
        return jdbcTemplate.update(sql, deckId) > 0;
    }

    @Override
    public int insert(int deckId, int cardId, int quantity) {
        final String sql = """
            INSERT INTO deck_card (deck_id, card_id, quantity)
            VALUES (?, ?, ?)
        """;

        KeyHolder kh = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, deckId);
            ps.setInt(2, cardId);
            ps.setInt(3, Math.max(quantity, 1)); // guard: never below 1
            return ps;
        }, kh);

        Number key = kh.getKey();
        return (key != null) ? key.intValue() : 0; // return new deck_card_id
    }
}
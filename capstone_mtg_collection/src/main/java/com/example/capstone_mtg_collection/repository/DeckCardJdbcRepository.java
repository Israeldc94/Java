package com.example.capstone_mtg_collection.repository;

import com.example.capstone_mtg_collection.mapper.DeckCardMapper;
import com.example.capstone_mtg_collection.model.DeckCard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeckCardJdbcRepository implements DeckCardRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DeckCardMapper mapper;

    public DeckCardJdbcRepository(JdbcTemplate jdbcTemplate, DeckCardMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<DeckCard> findByDeckId(int deckId) {
        String sql = "SELECT * FROM deck_card WHERE deck_id = ?";
        return jdbcTemplate.query(sql, mapper, deckId);
    }

    @Override
    public DeckCard findByDeckIdAndCardId(int deckId, int cardId) {
        String sql = "SELECT * FROM deck_card WHERE deck_id = ? AND card_id = ?";
        return jdbcTemplate.queryForObject(sql, mapper, deckId, cardId);
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
}
// src/main/java/.../repository/jdbc/CommanderCardJdbcRepository.java
package com.example.capstone_mtg_collection.repository.jdbc;

import com.example.capstone_mtg_collection.mapper.CardMapper;
import com.example.capstone_mtg_collection.mapper.CommanderCardMapper;
import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.CommanderCard;
import com.example.capstone_mtg_collection.repository.CommanderCardRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class CommanderCardJdbcRepository implements CommanderCardRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CommanderCardMapper ccmapper;
    private final CardMapper cMapper;

    public CommanderCardJdbcRepository(JdbcTemplate jdbcTemplate, CommanderCardMapper ccMapper, CardMapper cMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.ccmapper = ccMapper;
        this.cMapper = cMapper;
    }

    @Override
    public List<CommanderCard> findByDeckId(int deckId) {
        final String sql = """
            SELECT commander_card_id, deck_id, slot, card_id
            FROM commander_card
            WHERE deck_id = ?
            ORDER BY slot
            """;
        return jdbcTemplate.query(sql, ccmapper, deckId);
    }

    @Override
    public CommanderCard findByDeckIdAndSlot(int deckId, int slot) {
        final String sql = """
            SELECT commander_card_id, deck_id, slot, card_id
            FROM commander_card
            WHERE deck_id = ? AND slot = ?
            """;
        var list = jdbcTemplate.query(sql, ccmapper, deckId, slot);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int insert(int deckId, int cardId, int slot) {
        final String sql = """
            INSERT INTO commander_card (deck_id, card_id, slot)
            VALUES (?, ?, ?)
        """;

        KeyHolder kh = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, deckId);
            ps.setInt(2, cardId);
            ps.setInt(3, slot);
            return ps;
        }, kh);

        Number key = kh.getKey();
        return (key != null) ? key.intValue() : 0;
    }

    @Override
    public int insertCom(CommanderCard cc) {
        final String sql = """
            INSERT INTO commander_card (deck_id, slot, card_id)
            VALUES (?, ?, ?)
            """;
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cc.getDeckId());
            ps.setInt(2, cc.getSlot());
            ps.setInt(3, cc.getCardId());
            return ps;
        }, kh);
        cc.setCommanderCardId(Objects.requireNonNull(kh.getKey()).intValue());
        return 1;
    }

    @Override
    public int update(CommanderCard cc) {
        final String sql = """
            UPDATE commander_card
            SET card_id = ?
            WHERE deck_id = ? AND slot = ?
            """;
        return jdbcTemplate.update(sql, cc.getCardId(), cc.getDeckId(), cc.getSlot());
    }

    @Override
    public int deleteByDeckId(int deckId) {
        return jdbcTemplate.update("DELETE FROM commander_card WHERE deck_id = ?", deckId);
    }

    @Override
    public int deleteByDeckIdAndSlot(int deckId, int slot) {
        return jdbcTemplate.update("DELETE FROM commander_card WHERE deck_id = ? AND slot = ?", deckId, slot);
    }

    @Override
    public List<Card> findCommanderCards(int deckId) {
        final String sql = """
            SELECT c.card_id, c.name, c.color, c.type_line, c.foil,
                   c.quantity_owned, c.image_url, c.mana_cost_text, c.mana_value
              FROM commander_card cc
              JOIN card c ON c.card_id = cc.card_id
             WHERE cc.deck_id = ?
             ORDER BY cc.slot ASC
        """;
        return jdbcTemplate.query(sql, cMapper, deckId);
    }

}


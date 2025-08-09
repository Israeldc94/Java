package com.example.capstone_mtg_collection.mapper;

import com.example.capstone_mtg_collection.model.DeckCard;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DeckCardMapper implements RowMapper<DeckCard> {

    @Override
    public DeckCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DeckCard(
                rs.getInt("deck_id"),
                rs.getInt("card_id"),
                rs.getInt("quantity")
        );
    }
}
